package com.expenser.events;

import org.springframework.context.ApplicationEvent;

import com.expenser.Entity.User;
import com.expenser.model.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendUserEmailEvent extends ApplicationEvent {

	private String emailType;
	
	private UserDTO user;
	
	private String appUrl;

	public SendUserEmailEvent(String emailType, UserDTO user, String appUrl) {
		super(user);
		this.emailType = emailType;
		this.user = user;
		this.appUrl = appUrl;
	}
	
}
