package com.expenser.model;

import com.expenser.Entity.User;
import com.expenser.Entity.UserLabel;
import com.expenser.Entity.UserRecord;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordLabelDTO {
	private String identifier;
	private String userLabelIdentifier;
	private ClientDTO client;
	private String recordIdentifier;
	private UserLabel userLabel;
	public RecordLabelDTO(String identifier, ClientDTO client, UserLabel userLabel) {
		
		this.identifier = identifier;
		this.client = client;
		this.userLabel = userLabel;
	}
	public RecordLabelDTO() {
		
	}
	
	
}
