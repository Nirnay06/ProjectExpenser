package com.expenser.model;

import java.util.Set;


import com.expenser.Entity.Authority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	private String username;
	private String name;
	private Set<Authority> authorities;
	private String userIdentifier;
	private String firstname;
	private String lastname;
	
	public UserDTO(String username,Set<Authority> authorities, String userIdentifier, String firstName, String lastName) {
		this.username = username;
		this.authorities = authorities;
		this.userIdentifier = userIdentifier;
		this.firstname = firstName;
		this.lastname = lastName;
	}
	public UserDTO() {
	}
}
