package com.expenser.model;

import java.io.Serializable;

import com.expenser.Entity.UserLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLabelDTO implements Serializable{

	private String identifier;
	private String clientIdentifier;
	private boolean archive;
	private String title;
	private String color;
	private boolean defaultAssign;
	
	
	public UserLabelDTO(String identifier, String clientIdentifier, boolean archive, String title, String color,
			boolean defaultAssign) {
		this.identifier = identifier;
		this.clientIdentifier = clientIdentifier;
		this.archive = archive;
		this.title = title;
		this.color = color;
		this.defaultAssign = defaultAssign;
	}
	
	public static UserLabelDTO convertEntityToDTO(UserLabel l) {
		return new UserLabelDTO(l.getIdentifier(), l.getClient().getClientIdentifier(), l.isArchive(), l.getTitle(),
				l.getColor(), l.isDefaultAssign());
	}
	
}
