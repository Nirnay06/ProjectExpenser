package com.expenser.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

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
@Table(name ="record_location")
@Getter
@Setter
@Where(clause = "deleted=CAST(0 as boolean)")
@SQLDelete(sql = "update record_location set deleted=cast(1 as boolean) where id=?")
public class RecordLocation extends AuditEntity implements Serializable{

	@Id
	@SequenceGenerator(name="recordLocationSequence", sequenceName = "RECORD_LOCATION_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "recordLabelSequence", strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name ="identifier")
	private String identifier;
	
	@Column(name ="latitude")
	private BigDecimal latitude;
	
	@Column(name ="longitude")
	private BigDecimal longitude;
	
	@Column(name ="title")
	private String title;
	
	@Column(name ="address_line")
	private String addressLine;
	
	@Column(name ="city")
	private String city;
	
	@Column(name ="state")
	private String state;
	
	@Column(name ="country")
	private String country;
	
	@Column(name ="modified")
	private boolean modified;
	
	@PrePersist
	public void PrePersist() {
		if(this.identifier==null) {
			this.identifier=UUID.randomUUID().toString();
		}
	}
	
	public RecordLocation() {
		
	}

	public RecordLocation(String identifier, BigDecimal latitude, BigDecimal longitude,
			String title, String addressLine, String city, String state, String country, boolean modified) {
		this.identifier = identifier;
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.country = country;
		this.modified = modified;
	}
}
