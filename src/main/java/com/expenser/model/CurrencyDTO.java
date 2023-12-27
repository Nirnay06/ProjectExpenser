package com.expenser.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyDTO {

	private String identifier;
	private ClientDTO client;
	private String clientIdentifier;
	private String currencyTitle;
	private String currencyIcon;
	private String currencyAbbreviation;
	private BigDecimal currencyRate;
	private boolean rateOverriden;
	private boolean baseCurrency;
	private String masterCurrencyIdentifier;
	private long displayOrder;
	
	public CurrencyDTO() {
		
	}
	
	public CurrencyDTO(String identifier, String clientIdentifier, String currencyTitle, String currencyIcon, BigDecimal currencyRate,
			boolean rateOverriden, boolean baseCurrency, String masterCurrencyIdentifier, String currencyabbreviation, long displayOrder) {
		this.identifier = identifier;
		this.clientIdentifier = clientIdentifier;
		this.currencyTitle = currencyTitle;
		this.currencyIcon = currencyIcon;
		this.currencyRate = currencyRate;
		this.rateOverriden = rateOverriden;
		this.baseCurrency = baseCurrency;
		this.masterCurrencyIdentifier = masterCurrencyIdentifier;
		this.currencyAbbreviation = currencyabbreviation;
		this.displayOrder = displayOrder;
	}
	
	
	
}
