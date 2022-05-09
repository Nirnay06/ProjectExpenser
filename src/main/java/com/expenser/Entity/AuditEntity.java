package com.expenser.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditEntityListner.class)
@Getter
@Setter
public class AuditEntity {
	
	@CreatedBy
	@Column(name="created_by")
	private String createdBy;
	
	@CreatedDate
	@Column(name ="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@LastModifiedBy
	@Column(name="updated_by")
	private String updatedBy;
	
	@LastModifiedDate
	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	private boolean deleted = Boolean.FALSE;
	
}
