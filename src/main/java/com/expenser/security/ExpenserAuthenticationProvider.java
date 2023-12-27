package com.expenser.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.expenser.Entity.Authority;
import com.expenser.Entity.User;
import com.expenser.repository.UserRepository;
import com.expenser.service.UserService;

@Component
public class ExpenserAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String pwd = authentication.getCredentials().toString();
		User user = userService.getUserByUsername(username);
		if(user !=null) {	
			if(user.isEnabled()) {
				if(passwordEncoder.matches(pwd, user.getPassword())) {
					return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAutoritries(user.getAuthorities()));
				}else {
					throw new BadCredentialsException("Invalid Username/Password");
				}				
			}else {
				throw new BadCredentialsException("The user is not activated. Please check your email for activation email or use the forget password links.");
			}
		}else {
			throw new BadCredentialsException("Invalid Username/Password");
		}
	}

	private Collection<? extends GrantedAuthority> getGrantedAutoritries(Set<Authority> authorities) {
		List<GrantedAuthority> grantedAutorities= new ArrayList<GrantedAuthority>();
		for(Authority a : authorities) {
			grantedAutorities.add(new SimpleGrantedAuthority(a.getName()));
		}
		return grantedAutorities;
	}

	@Override
	public boolean supports(Class<?> authenticationType) {
		return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
	}

}
