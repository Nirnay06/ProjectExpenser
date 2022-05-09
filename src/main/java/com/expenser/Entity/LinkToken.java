package com.expenser.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.expenser.enums.LinkTokenType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LinkToken extends AuditEntity{

	@Id
	@GeneratedValue(generator ="LinkTokenSeq" )
	@SequenceGenerator(name = "LinkTokenSeq",allocationSize = 1, sequenceName = "LINK_TOKEN_SEQ")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name= "generation_time")
	private Date generationTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expiration_time")
	private Date expirationTime;
	
	@Column(name = "token_identifier")
	private String tokenIdentifier;
	
	@Column(name = "user_identifier")
	private String userIdentifier;
	
	@Column(name="token_type")
	private LinkTokenType tokenType;
	
	@Column(name= "is_active")
	private boolean isActive = Boolean.TRUE;
}
