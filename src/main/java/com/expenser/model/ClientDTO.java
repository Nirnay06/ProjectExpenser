package com.expenser.model;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {

	private String fullName;
	private String userIdentifier;
	private String firstName;
	private String lastName;
	private UserDTO user;
	
	public ClientDTO() {
	}

	public ClientDTO(String fullName, String userIdentifier, String firstName, String lastName, UserDTO user) {
		super();
		this.fullName = fullName;
		this.userIdentifier = userIdentifier;
		this.firstName = firstName;
		this.lastName = lastName;
		this.user = user;
	}
}
