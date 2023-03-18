package com.expenser.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyDTO {

	private String identifier;
	private UserDTO user;
	private String title;
	private String icon;
	private BigDecimal currencyRate;
	private boolean rateOverriden;
	private boolean baseCurrency;
	private MasterCurrencyDTO currency;
	public CurrencyDTO() {
		
	}
	public CurrencyDTO(String identifier, UserDTO user, String title, String icon, BigDecimal currencyRate,
			boolean rateOverriden, boolean baseCurrency, MasterCurrencyDTO currency) {
		this.identifier = identifier;
		this.user = user;
		this.title = title;
		this.icon = icon;
		this.currencyRate = currencyRate;
		this.rateOverriden = rateOverriden;
		this.baseCurrency = baseCurrency;
		this.currency = currency;
	}
	
	
}
