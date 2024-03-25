package com.expenser.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.SecretKey;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expenser.Entity.User;
import com.expenser.enums.AuthProvider;
import com.expenser.model.APIResponseDTO;
import com.expenser.model.LoginRequestDTO;
import com.expenser.model.SignupRequestDTO;
import com.expenser.model.UserDTO;
import com.expenser.security.SecurityConstants;
import com.expenser.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/auth")
public class AuthRestController {
	
	Logger logger = LogManager.getLogger(AuthRestController.class);
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest, HttpServletRequest request,
			HttpServletResponse response) {
		loginRequest.setUsername(loginRequest.getUsername().toLowerCase());
		try{
			Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		if (auth != null) {
			UserDTO user = mapper.map(userService.getUserByUsername((String)auth.getPrincipal()), UserDTO.class);
			user.setAuthorities(null);
			SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
			String jwt = Jwts.builder().setIssuer("Expenser").setSubject("JWT Token")
					.claim("username", auth.getPrincipal())
					.claim("user", user)
					.claim("authorities", getAuthoritiesString(auth.getAuthorities()))
					.claim("issuedAt", new Date()).claim("expiredAt",new Date(new Date().getTime() + 3600000))
					.setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime() + 3600000)).signWith(key).compact();
			response.setHeader(SecurityConstants.JWT_HEADER, jwt);
			return new ResponseEntity<>(auth, HttpStatus.OK);
		}
		}catch(BadCredentialsException e) {
			return new ResponseEntity<>(new APIResponseDTO(e.getMessage(), false), HttpStatus.FORBIDDEN);
		}catch(Exception e ) {
			logger.error(e);
			return new ResponseEntity<>(new APIResponseDTO("Something went wrong", false), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}
	private String getAuthoritiesString(Collection<? extends GrantedAuthority> collection) {
		Set<String> grantedAuthorities = new HashSet<String>();
		for (GrantedAuthority a : collection) {
			grantedAuthorities.add(a.getAuthority());
		}
		return String.join(",", grantedAuthorities); 
	}
	@PostMapping("/signup")
	public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequestDTO signupRequest, HttpServletRequest request) {
		try {
			String appURL = request.getContextPath();
			signupRequest.setUsername(signupRequest.getUsername().toLowerCase());
			if (signupRequest.getUsername() != null && signupRequest.getPassword() != null) {
				
				if (!userService.checkIfUsernameExists(signupRequest.getUsername())) {
					signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
					signupRequest.setProvider(AuthProvider.local);
					userService.addUser(signupRequest, appURL);
					return new ResponseEntity<>(new APIResponseDTO("User added successfully. A confirmation email will be sent to you inbox.", true), HttpStatus.OK);
				} else {
					return new ResponseEntity<>(new APIResponseDTO("User already exists with this username", false),
							HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<>(new APIResponseDTO("Username/Password cannot be null", false),
						HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			logger.error(e);
			return new ResponseEntity<>(new APIResponseDTO("Something went wrong", false),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/registrationConfirm")
	public ResponseEntity<?> confirmUserRegistration(@RequestParam("token") String tokenIdentifier){
		boolean success = userService.confirmUserRegistration(tokenIdentifier);
		if(success) {
			return new ResponseEntity<>(new APIResponseDTO("User Activated Successfully", success), HttpStatus.OK);
		}
		return new ResponseEntity<>(new APIResponseDTO("Invalid or Expired Token", success),HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/sendConfirmationLink")
	public ResponseEntity<?> sendNewConfirmationLinkToUser(@RequestBody SignupRequestDTO signupRequest){
		if(signupRequest.getUsername()!=null && signupRequest.isValidUsername()) {
			try{
				userService.sendConfirmationLink(signupRequest.getUsername());
				return new ResponseEntity<>(new APIResponseDTO("Confirmation email sent successfully", true), HttpStatus.OK);
			}catch(BadCredentialsException e) {
				return new ResponseEntity<>(new APIResponseDTO(e.getMessage(), false), HttpStatus.BAD_REQUEST);
			}catch(Exception e) {
				logger.error(e);
				return new ResponseEntity<>(new APIResponseDTO("Something went wrong", false), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(new APIResponseDTO("Enter a valid username", false), HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/sendResetLink")
	public ResponseEntity<?> sendResetLinkToUser(@RequestBody SignupRequestDTO signupRequest){
		if(signupRequest.getUsername()!=null && signupRequest.isValidUsername()) {
			try{
				userService.sendResetLink(signupRequest.getUsername());
				return new ResponseEntity<>(new APIResponseDTO("Reset email sent successfully", true), HttpStatus.OK);
			}catch(BadCredentialsException e) {
				return new ResponseEntity<>(new APIResponseDTO(e.getMessage(), false), HttpStatus.BAD_REQUEST);
			}catch(Exception e) {
				logger.error(e.getStackTrace().toString());
				return new ResponseEntity<>(new APIResponseDTO("Something went wrong", false), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(new APIResponseDTO("Enter a valid username", false), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/fetchUserDetialsForToken")
	public ResponseEntity<?> fetchUserDetialsForToken(@RequestParam("token") String tokenIdentifier) {
		try {
				UserDTO user = userService.findUserByTokenIdentifier(tokenIdentifier);
				if (user != null) {
					return new ResponseEntity<>(user, HttpStatus.OK);
				}
			} catch (BadCredentialsException e) {
				return new ResponseEntity<>(new APIResponseDTO(e.getMessage(), false), HttpStatus.BAD_REQUEST);
			} catch (Exception e) {
				logger.error(e.getStackTrace().toString());
				return new ResponseEntity<>(new APIResponseDTO("Something went wrong", false),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		return null;
	}
	
	
	@PostMapping("/resetPassword")
	public ResponseEntity<?> resetPassword(@Valid @RequestBody SignupRequestDTO signupRequest, HttpServletRequest request) {
		try {
			String appURL = request.getContextPath();
			signupRequest.setUsername(signupRequest.getUsername().toLowerCase());
			if (signupRequest.getUsername() != null && signupRequest.getPassword() != null) {
				signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
				userService.updatePassword(signupRequest);
				return new ResponseEntity<>(new APIResponseDTO("Password reset successfully", true),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new APIResponseDTO("Username/Password cannot be null", false),
						HttpStatus.BAD_REQUEST);
			}
		}catch(BadCredentialsException e) {
			return new ResponseEntity<>(new APIResponseDTO(e.getMessage(), false),
					HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			logger.error(e.getStackTrace().toString());
			return new ResponseEntity<>(new APIResponseDTO("Something went wrong", false),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
