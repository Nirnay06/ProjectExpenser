package com.expenser.Entity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.expenser.util.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends AuditEntity{
	
	@Id
	@SequenceGenerator(name = "UserSequence", sequenceName = "USER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UserSequence")
	private Long id;
	
	@Column(name = "user_identifier")
	private String userIdentifier;
	private String username;
	
	@JsonIgnore
	private String password;
	
	@Column(name = "full_name")
	private String name;
	
	@Column(name = "first_name")
	private String firstname;
	
	@Column(name = "last_name")
	private String lastname;
	
	@JsonIgnore
	@Column(name ="enabled")
	private boolean enabled = Boolean.FALSE;
	
	 @JsonIgnoreProperties("user")
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Authority> authorities;

	public User() {
		
	}
	
	public User(String username, String password, String firstName, String lastName, Set<Authority> authorities) {
		this.userIdentifier = UUID.randomUUID().toString();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.firstname= firstName;
		this.lastname= lastName;
	}
	
	@PrePersist
	public void prePersists() {
		if(this.userIdentifier==null) {
			this.userIdentifier=UUID.randomUUID().toString();
		}
		this.name = this.firstname.concat(Constants.NAME_DELIMITER).concat(this.lastname);
	}

}
