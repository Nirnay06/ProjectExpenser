package com.expenser.util;

import java.nio.file.AccessDeniedException;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.expenser.Entity.User;
import com.expenser.exception.BusinessException;
import com.expenser.model.ClientDTO;
import com.expenser.model.UserDTO;


public class SecurityUtils {

	private static ClientDTO client = null;
	
	private static String baseUrl = null;
	
	/*<p>This function will return the user from Spring session</p>
	 * @return userDTO -> the detail of the user
	 * @throw AccessDenied Exception
	 */
	public static UserDTO getLoggedUserFromSession() throws  AccessDeniedException {
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
	
	public static ClientDTO getClientFromSession() throws  AccessDeniedException, BusinessException {
		if(client !=null && getLoggedUserFromSession()!=null) {
			return client;	
		}
		return null;
	}

	public static void setClientDetails(ClientDTO clientDTO) {
		client = clientDTO;
	}
	
	public static void setBaseUrl(String url) {
		baseUrl= url;
	}
	
	public static String getBaseUrl() {
		return baseUrl;
	}
}
