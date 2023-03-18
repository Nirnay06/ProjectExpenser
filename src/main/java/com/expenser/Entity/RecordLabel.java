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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="record_labels")
@Getter
@Setter
@Where(clause = "deleted !=1")
@SQLDelete(sql = "update record_labels set deleted=1 where id=?")
public class RecordLabel  extends AuditEntity implements Serializable{

	@Id
	@SequenceGenerator(name="recordLabelSequence", sequenceName = "RECORD_LABEL_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "recordLabelSequence", strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name  ="identifier")
	private String identifier;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name ="record_identifier", referencedColumnName = "identifier")
	private UserRecord record;
	
	@OneToOne()
	@JoinColumn(name ="user_label_identifier", referencedColumnName = "identifier")
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
