package com.expenser.Entity;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="record_labels")
@Getter
@Setter
@Where(clause = "deleted=CAST(0 as boolean)")
@SQLDelete(sql = "update record_labels set deleted=cast(1 as boolean) where id=?")
public class RecordLabel  extends AuditEntity implements Serializable{

	@Id
	@SequenceGenerator(name="recordLabelSequence", sequenceName = "RECORD_LABEL_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "recordLabelSequence", strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name  ="identifier")
	private String identifier;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="record_id")
	private UserRecord record;
	
	@OneToOne()
	@JoinColumn(name ="user_label_id")
	private UserLabel userLabel;
	
	@PrePersist
	public void PrePersist() {
		if(this.identifier==null) {
			this.identifier=UUID.randomUUID().toString();
		}
	}
	
	public RecordLabel() {
		
	}

	public RecordLabel( UserRecord record, UserLabel userLabel) {
		this.record = record;
		this.userLabel = userLabel;
	}
}
