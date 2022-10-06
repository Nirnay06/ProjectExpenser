package com.expenser.Entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="user_labels")
@Getter
@Setter
@Where(clause = "deleted !=1")
@SQLDelete(sql = "update user_labels set deleted=1 where id=?")
public class UserLabel extends AuditEntity{

	@Id
	@SequenceGenerator(name = "userLabelSequence",sequenceName = "USER_LABEL_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "userLabelSequence", strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "identifier")
	private String identifier;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_identifier")
	private User user;
	
	@Column(name ="is_archive")
	private boolean archive;
	
	@Column(name ="title")
	private String title;
	
	@Column(name ="color")
	private String color;
	
	@Column(name="default_assign")
	private boolean defaultAssign;
	
	public UserLabel () {
		
	}

	public UserLabel(String identifier, User user, boolean archive, String title, String color, boolean defaultAssign) {
		this.identifier = identifier;
		this.user = user;
		this.archive = archive;
		this.title = title;
		this.color = color;
		this.defaultAssign = defaultAssign;
	}
	
	
	@PrePersist
	public void PrePersist() {
		if(this.identifier==null) {
			this.identifier=UUID.randomUUID().toString();
		}
	}
}
