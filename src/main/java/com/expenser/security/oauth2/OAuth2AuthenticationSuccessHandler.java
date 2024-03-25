package com.expenser.security.oauth2;


import com.expenser.Entity.User;
import com.expenser.enums.AuthProvider;
import com.expenser.exception.BadRequestException;
import com.expenser.exception.BusinessException;
import com.expenser.model.SignupRequestDTO;
import com.expenser.model.UserDTO;
import com.expenser.repository.UserRepository;
import com.expenser.security.SecurityConstants;
import com.expenser.security.UserPrincipal;
import com.expenser.security.oauth2.user.OAuth2UserInfo;
import com.expenser.security.oauth2.user.OAuth2UserInfoFactory;
import com.expenser.service.UserService;
import com.expenser.util.CookieUtils;
import com.expenser.util.SecurityUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.expenser.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper mapper;
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;
	
	@Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication){
        Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
                .map(Cookie::getValue);

        if(redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
            throw new BadRequestException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }
        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
        UserPrincipal userPrincipal =  (UserPrincipal) authentication.getPrincipal();

        List<User> user = userRepository.findByUsername(userPrincipal.getEmail());
        UserDTO userDTO= null;
        if(CollectionUtils.isNotEmpty(user)) {
        	userDTO = mapper.map(user.get(0), UserDTO.class);
        	userDTO.setAuthorities(null);
        }else {
        	throw new BadRequestException("User Not Found.");
        }
		SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
		
        String token = Jwts.builder().setIssuer("Expenser").setSubject("JWT Token")
				.claim("username", userPrincipal.getEmail())
				.claim("user", userDTO)
				.claim("authorities", getAuthoritiesString(authentication.getAuthorities()))
				.claim("issuedAt", new Date()).claim("expiredAt",new Date(new Date().getTime() + 3600000))
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + 3600000)).signWith(key).compact();
        response.setHeader(SecurityConstants.JWT_HEADER, token);
        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("Authorization", token)
                .build().toUriString();
    }

    private String getAuthoritiesString(Collection<? extends GrantedAuthority> collection) {
		Set<String> grantedAuthorities = new HashSet<String>();
		for (GrantedAuthority a : collection) {
			grantedAuthorities.add(a.getAuthority());
		}
		return String.join(",", grantedAuthorities); 
	}
    
    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private boolean isAuthorizedRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);

        return new ArrayList<>(Arrays.asList("http://localhost:3000/oauth2/redirect","myandroidapp://oauth2/redirect","myiosapp://oauth2/redirect"))
                .stream()
                .anyMatch(authorizedRedirectUri -> {
                    // Only validate host and port. Let the clients use different paths if they want to
                    URI authorizedURI = URI.create(authorizedRedirectUri);
                    if(authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
                            && authorizedURI.getPort() == clientRedirectUri.getPort()) {
                        return true;
                    }
                    return false;
                });
    }
    
    
    private UserDTO processOAuth2User(String clientRegisterationId, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(clientRegisterationId, oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new BadRequestException("Email not found from OAuth2 provider");
        }

        List<User> userlist = userRepository.findByUsername(oAuth2UserInfo.getEmail());
        User user;
        if(CollectionUtils.isNotEmpty(userlist)) {
            user = userlist.get(0);
            if(!user.getProvider().equals(AuthProvider.valueOf(clientRegisterationId))) {
                throw new BadRequestException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(clientRegisterationId, oAuth2UserInfo);
        }
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        userDTO.setAuthorities(null);
        return userDTO;
    }

    private User registerNewUser(String clientRegisterationId, OAuth2UserInfo oAuth2UserInfo) {
        SignupRequestDTO signupRequestDTO = new SignupRequestDTO();

        signupRequestDTO.setProvider(AuthProvider.valueOf(clientRegisterationId));
        signupRequestDTO.setProviderId(oAuth2UserInfo.getId());
        signupRequestDTO.setFullname(oAuth2UserInfo.getName());
        signupRequestDTO.setUsername(oAuth2UserInfo.getEmail());
        signupRequestDTO.setImageUrl(oAuth2UserInfo.getImageUrl());
        
        return userService.addUser(signupRequestDTO,SecurityUtils.getBaseUrl());
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userRepository.save(existingUser);
    }

}
