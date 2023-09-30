package com.expenser.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntervalBalanceDTO {
	
	private String parameterName;
	private String interval;
	private Long amount;
	
	
	public IntervalBalanceDTO(String parameterName, String interval, Long amount) {
		this.parameterName = parameterName;
		this.interval = interval;
		this.amount = amount;
	}
	
	
}
