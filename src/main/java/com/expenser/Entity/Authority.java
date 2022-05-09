package com.expenser.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="authorities")
public class Authority extends AuditEntity {
	
	@Id
	@SequenceGenerator(name = "AuthoritySequence", sequenceName = "AUTHORITY_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AuthoritySequence")
	private long id;
	
	@JsonIgnoreProperties("authorities")
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private String name;

	public Authority() {
	}
	public Authority(String name) {
		this.name = name;
	}

	
}
