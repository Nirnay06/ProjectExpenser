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
	private String title;
	private String icon;
	private BigDecimal currencyRate;
	private boolean rateOverriden;
	private boolean baseCurrency;
	private MasterCurrencyDTO currency;
	private String masterCurrencyIdentifier;
	
	public CurrencyDTO() {
		
	}
	public CurrencyDTO(String identifier, ClientDTO client, String title, String icon, BigDecimal currencyRate,
			boolean rateOverriden, boolean baseCurrency, MasterCurrencyDTO currency) {
		this.identifier = identifier;
		this.client = client;
		this.title = title;
		this.icon = icon;
		this.currencyRate = currencyRate;
		this.rateOverriden = rateOverriden;
		this.baseCurrency = baseCurrency;
		this.currency = currency;
	}
	public CurrencyDTO(String identifier, String clientIdentifier, String title, String icon, long currencyRate,
			boolean rateOverriden, boolean baseCurrency, String masterCurrencyIdentifier) {
		this.identifier = identifier;
		this.clientIdentifier = clientIdentifier;
		this.title = title;
		this.icon = icon;
		this.currencyRate = new BigDecimal(String.valueOf(currencyRate));
		this.rateOverriden = rateOverriden;
		this.baseCurrency = baseCurrency;
		this.masterCurrencyIdentifier = masterCurrencyIdentifier;
	}
	
	
	
}
