package com.expenser.Entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.expenser.util.Constants;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name= "client")
@Where(clause = "deleted !=1")
@SQLDelete(sql = "update users set deleted=1 where id=?")
@Getter
@Setter
public class Client extends AuditEntity implements Serializable{
	
	@Id
	@SequenceGenerator(name = "ClientSequence", sequenceName = "CLIENT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ClientSequence")
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "full_name")
	private String fullName;
	
	@JoinColumn(name = "user_identifier", referencedColumnName = "user_identifier")
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
	@Column(name = "client_identifier")
	private String clientIdentifier;
	
	
	public Client(User user) {
		this.user= user;
		this.firstName= user.getFirstname();
		this.lastName = user.getLastname();
		this.fullName = user.getName();
	}
	
	public Client() {
		
	}


	@PrePersist
	public void prePersists() {
		if(this.clientIdentifier==null) {
			this.clientIdentifier=UUID.randomUUID().toString();
		}
		this.fullName = this.firstName.concat(Constants.NAME_DELIMITER).concat(this.lastName);
	}
}
