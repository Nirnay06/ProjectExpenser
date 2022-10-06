package com.expenser.Entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user_category")
@Getter
@Setter
@Where(clause = "deleted !=1")
@SQLDelete(sql = "update user_category set deleted=1 where id=?")
public class RecordUserCategory extends AuditEntity{

	@Id
	@SequenceGenerator(name = "userRecordCatSeq", sequenceName = "USER_RECORD_CAT_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "userRecordCatSeq", strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name ="identifier")
	private String identifier;
	
	@ManyToOne(fetch =FetchType.LAZY )
	@JoinColumn(name ="user_identifier")
	private User user;
	
	@Column(name ="category_title")
	private String title;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_identifier")
	private RecordUserCategory parent;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")	
	private List<RecordUserCategory> children;
	
	@Column(name ="icon")
	private String icon;
	
	@Column(name ="color")
	private String color;
	
	@Column(name ="hidden")
	private boolean hidden;
	
	@Column(name ="is_default_category")
	private boolean defaultCategory;

	@Transient
	@OneToOne(mappedBy = "category")
	private UserRecord record;
	
	public RecordUserCategory() {
	}

	public RecordUserCategory(String identifier, User user, List<RecordUserCategory> children, String icon,
			String color, boolean hidden, boolean defaultCategory) {
		this.identifier = identifier;
		this.user = user;
		this.children = children;
		this.icon = icon;
		this.color = color;
		this.hidden = hidden;
		this.defaultCategory = defaultCategory;
	}
	
	@PrePersist
	public void PrePersist() {
		if(this.identifier==null) {
			this.identifier=UUID.randomUUID().toString();
		}
	}
}
