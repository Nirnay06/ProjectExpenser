package com.expenser.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expenser.Entity.Authority;
import com.expenser.Entity.LinkToken;
import com.expenser.Entity.User;
import com.expenser.api.ClientService;
import com.expenser.api.LinkTokenService;
import com.expenser.api.UserService;
import com.expenser.enums.AuthorityEnum;
import com.expenser.enums.EmailType;
import com.expenser.enums.LinkTokenType;
import com.expenser.events.SendUserEmailEvent;
import com.expenser.model.SignupRequestDTO;
import com.expenser.model.UserDTO;
import com.expenser.repository.UserRepository;
import com.expenser.util.Constants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	LinkTokenService linkTokenService;
	
	@Autowired
	ClientService clientService;

	@Override
	public User getUserByUsername(String username) {
		List<User> users=  userRepository.findByUsername(username);
		if(users.size() > 0 ) {
			return users.get(0);
		}
		return null;
	}

	@Transactional
	@Override
	public void addUser(SignupRequestDTO signupRequest, String appURL) {
		User user = new User();
		user.setFirstname(signupRequest.getFirstname());
		user.setLastname(signupRequest.getLastname());
		user.setUsername(signupRequest.getUsername());
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		Set<Authority> auths = new HashSet<>();
		Authority auth = new Authority(AuthorityEnum.USER.name());
		auth.setUser(user);
		auths.add(auth);
		user.setAuthorities(auths);
		User newUser = userRepository.save(user);
		if(newUser!=null) {
			clientService.createNewClient(user);
		}
		sendRegistrationEmailToUser(user, appURL,EmailType.REGISTRATION_CONFIRM);
	}

	@Override
	public boolean checkIfUsernameExists(String username) {
		return getUserByUsername(username)!=null; 
	}
	
	private void sendRegistrationEmailToUser(User user, String appURL, EmailType emailType) {
		applicationEventPublisher.publishEvent(new SendUserEmailEvent(emailType.name(),
				modelMapper.map(user, UserDTO.class), appURL));
	}

	@Override
	@Transactional
	public boolean confirmUserRegistration(String tokenIdentifier) {
		LinkToken  token = linkTokenService.findByTokenIdentifierAndTokenType(tokenIdentifier, LinkTokenType.CONFIRM_REGISTRATION);
		if(token != null && linkTokenService.isTokenActive(token)) {
			User user = userRepository.findByUserIdentifier(token.getUserIdentifier());
			if(user !=null) {
				user.setEnabled(true);
				token.setActive(false);
				return true;
			}
		}
		return false;
	}

	@Override
	public void sendConfirmationLink(String username) {
		User user = getUserByUsername(username);
		if( user!=null) {
			linkTokenService.disablePreviousTokensForUserAndType(user.getUserIdentifier(), LinkTokenType.CONFIRM_REGISTRATION);
			sendRegistrationEmailToUser(user,null,EmailType.REGISTRATION_CONFIRM);
			return;
		}else {
			throw new BadCredentialsException("User doesn't exists");
		}
	}

	@Override
	public void sendResetLink(String username) {
		User user = getUserByUsername(username);
		if( user!=null) {
			if(user.isEnabled()) {
				linkTokenService.disablePreviousTokensForUserAndType(user.getUserIdentifier(), LinkTokenType.FORGET_PASSWORD);				sendRegistrationEmailToUser(user,null,EmailType.RESET_PASSWORD);
				return;
			}
			throw new BadCredentialsException("The user is not activated.");
		}else {
			throw new BadCredentialsException("User doesn't exists");
		}
	}

	@Override
	public UserDTO findUserByTokenIdentifier(String tokenIdentifier) {

		LinkToken token = linkTokenService.findByTokenIdentifier(tokenIdentifier);
		if (token != null && linkTokenService.isTokenActive(token)) {
			User user = userRepository.findByUserIdentifier(token.getUserIdentifier());
			if (user != null) {
				UserDTO userDTO = modelMapper.map(user, UserDTO.class);
				return userDTO;
			}
		}
		throw new BadCredentialsException("Token Invalid or Expired");
	}

	@Override
	public void updatePassword(SignupRequestDTO signupRequest) {
		User user = getUserByUsername(signupRequest.getUsername());
		if(user!=null) {	
			if(user.isEnabled()) {
				user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
				userRepository.save(user);				
			}
			throw new BadCredentialsException("The Username is not activated");
		}
		throw new BadCredentialsException("Username not found");
	}

	@Override
	public User findByUserIdentifier(String userIdentifier) {
		return userRepository.findByUserIdentifier(userIdentifier);
	}
}
