package com.expenser.api;


import javax.validation.Valid;

import org.springframework.stereotype.Service;
import com.expenser.Entity.User;
import com.expenser.model.SignupRequestDTO;
import com.expenser.model.UserDTO;


@Service
public interface UserService {
	public  User getUserByUsername(String username);

	public void addUser(SignupRequestDTO signupRequest, String appURL);

	boolean checkIfUsernameExists(String username);

	public boolean confirmUserRegistration(String tokenIdentifier);

	public void sendConfirmationLink(String username);

	public void sendResetLink(String username);

	public UserDTO findUserByTokenIdentifier(String tokenIdentifier);

	public void updatePassword(SignupRequestDTO signupRequest);
	
	public User findByUserIdentifier(String userIdentifier);
}
