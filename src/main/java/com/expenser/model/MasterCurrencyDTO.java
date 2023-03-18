package com.expenser.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterCurrencyDTO {
	private String identifier;
	private String currencySymbol;
	private BigDecimal conversionRate;
	private Date syncDate;
	public MasterCurrencyDTO(String identifier, String currencySymbol, BigDecimal conversionRate, Date syncDate) {
		this.identifier = identifier;
		this.currencySymbol = currencySymbol;
		this.conversionRate = conversionRate;
		this.syncDate = syncDate;
	}
	
	public MasterCurrencyDTO() {}
	
	
}
