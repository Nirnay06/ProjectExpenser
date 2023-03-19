package com.expenser.Entity;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="user_category")
@Getter
@Setter
@Where(clause = "deleted !=1")
@SQLDelete(sql = "update user_category set deleted=1 where id=?")
public class RecordUserCategory extends AuditEntity implements Serializable{

	@Id
	@SequenceGenerator(name = "userRecordCatSeq", sequenceName = "USER_RECORD_CAT_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "userRecordCatSeq", strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name ="identifier")
	private String identifier;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_identifier", referencedColumnName = "client_identifier")
	private Client client;
	
	@Column(name ="category_title")
	private String title;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_identifier", referencedColumnName = "identifier")
	private RecordUserCategory parent;

	@JsonIgnoreProperties(value = "parent")
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

	@Column(name = "category_order")
	private Integer order;
	
	@Column(name ="selectable")
	private boolean selectable;
	
	public RecordUserCategory() {
	}

	public RecordUserCategory(String identifier, Client client, List<RecordUserCategory> children, String icon,
			String color, boolean hidden, boolean defaultCategory, Integer order, boolean selectable) {
		this.identifier = identifier;
		this.client = client;
		this.children = children;
		this.icon = icon;
		this.color = color;
		this.hidden = hidden;
		this.defaultCategory = defaultCategory;
		this.order = order;
		this.selectable = selectable;
	}
	
	@PrePersist
	public void PrePersist() {
		if(this.identifier==null) {
			this.identifier=UUID.randomUUID().toString();
		}
	}
}
