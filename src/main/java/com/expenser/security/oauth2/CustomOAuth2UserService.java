package com.expenser.security.oauth2;


import com.expenser.Entity.User;
import com.expenser.enums.AuthProvider;
import com.expenser.exception.BadRequestException;
import com.expenser.exception.BusinessException;
import com.expenser.model.SignupRequestDTO;
import com.expenser.model.UserDTO;
import com.expenser.repository.UserRepository;
import com.expenser.security.UserPrincipal;
import com.expenser.security.oauth2.user.OAuth2UserInfo;
import com.expenser.security.oauth2.user.OAuth2UserInfoFactory;
import com.expenser.service.UserService;
import com.expenser.util.SecurityUtils;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper mapper;
	
    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
        	return processOAuth2User(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    public UserPrincipal processOAuth2User(String clientRegisterationId, OAuth2User oAuth2User) {
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
        user.setAuthorities(null);
        return UserPrincipal.create(user, oAuth2User.getAttributes());
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
