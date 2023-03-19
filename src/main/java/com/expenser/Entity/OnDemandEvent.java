package com.expenser.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="on_demand_event")
@Where(clause = "deleted=0")
public class OnDemandEvent extends AuditEntity{

	@Id
	@SequenceGenerator(name="onDemandEventSequence", sequenceName = "ON_DEMAND_EVENT_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "onDemandEventSequence", strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name ="key")
	private String key;
	
	@Column(name ="value")
	private String value;
	
	@Column(name ="active")
	private boolean active;
	
	@Lob
	@Column(name ="error_msg", columnDefinition = "BLOB")
	private String errorMsg;
}
