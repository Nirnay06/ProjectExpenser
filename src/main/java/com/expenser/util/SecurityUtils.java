package com.expenser.util;

import java.nio.file.AccessDeniedException;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.expenser.Entity.User;
import com.expenser.model.UserDTO;


public class SecurityUtils {

	
	/*<p>This function will return the user from Spring session</p>
	 * @return userDTO -> the detail of the user
	 * @throw AccessDenied Exception
	 */
	public static UserDTO getUserFromSession() throws  AccessDeniedException {
		ModelMapper modelMapper = new ModelMapper();
		Object u = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if(u instanceof User ) {
			UserDTO user =  modelMapper.map(u, UserDTO.class);
			if(user != null && user.getUserIdentifier() !=null) {
				return user;
			}
		}
		throw new AccessDeniedException(null);
	}
}
