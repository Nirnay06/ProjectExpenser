package com.expenser.model;

import java.math.BigDecimal;

import com.expenser.enums.AccountType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

	private ClientDTO client;
	private String identifier;
	private boolean active;
	private AccountType accountType;
	private String accountName;
	private String accountColor;
	private BigDecimal accountBalance;
	private String icon;
	private BigDecimal initialBalance;
	private boolean excludeFromStats;
	private boolean archived;
	private CurrencyDTO accountCurrency;
	public AccountDTO() {
	}
	public AccountDTO(ClientDTO client, String identifier, boolean active, AccountType accountType, String accountName,
			String accountColor, BigDecimal accountBalance, String icon, BigDecimal initialBalance, boolean excludeFromStats,
			boolean archived, CurrencyDTO accountCurrency) {
		this.client = client;
		this.identifier = identifier;
		this.active = active;
		this.accountType = accountType;
		this.accountName = accountName;
		this.accountColor = accountColor;
		this.accountBalance = accountBalance;
		this.icon = icon;
		this.initialBalance = initialBalance;
		this.excludeFromStats = excludeFromStats;
		this.archived = archived;
		this.accountCurrency = accountCurrency;
	}
	
	
	
}
