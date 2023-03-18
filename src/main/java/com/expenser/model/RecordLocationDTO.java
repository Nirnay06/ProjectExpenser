package com.expenser.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordLocationDTO {

	private String identifier;
	private ClientDTO client;
	private RecordDTO record;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private String title;
	private String addressLine;
	private String city;
	private String state;
	private String country;
	private boolean modified;
	public RecordLocationDTO( ClientDTO client, RecordDTO record, BigDecimal latitude, BigDecimal longitude,
			String title, String addressLine, String city, String state, String country) {
		
		this.client	= client;
		this.record = record;
		this.latitude = latitude;
		this.longitude = longitude;
		this.title = title;
		this.addressLine = addressLine;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	public RecordLocationDTO() {
		
	}
	
	
}
