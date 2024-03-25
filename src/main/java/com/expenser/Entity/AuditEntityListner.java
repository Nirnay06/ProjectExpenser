package com.expenser.Entity;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuditEntityListner {

	@PrePersist
	public void auditCreate(Object obj) {
		Date date = new Date();
		AuditEntity aud = (AuditEntity)obj;
		aud.setCreatedBy(getLoggedInUser());
		aud.setUpdatedBy(getLoggedInUser());
		aud.setCreatedDate(date);
		aud.setUpdatedDate(date);
	}
	

	@PreUpdate
	public void auditUpdate(Object obj) {
		Date date = new Date();
		AuditEntity aud = (AuditEntity)obj;
		aud.setUpdatedBy(getLoggedInUser());
		aud.setUpdatedDate(date);
	}
	private String getLoggedInUser() {
		
		String username = "SYSTEM";
		
		if(SecurityContextHolder.getContext().getAuthentication()!=null && SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null) {
			username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		}
		return username;
	}
}
