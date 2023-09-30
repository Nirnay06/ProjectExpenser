package com.expenser.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.expenser.enums.AccountType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDTO {

	private ClientDTO client;
	private String clientIdentifier;
	private String identifier;
	private boolean active;
	
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	private String accountName;
	private String accountColor;
	private long accountBalance;
	private String icon;
	private long initialBalance;
	private boolean excludeFromStats;
	private boolean archived;
	private CurrencyDTO accountCurrency;
	private String currenyIdentifier;
	public AccountDTO() {
	}
	public AccountDTO(ClientDTO client, String identifier, boolean active, AccountType accountType, String accountName,
			String accountColor, long accountBalance, String icon, long initialBalance, boolean excludeFromStats,
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
	public AccountDTO(String clientIdentifier, String identifier, boolean active, AccountType accountType,
			String accountName, String accountColor, long accountBalance, String icon, long initialBalance,
			boolean excludeFromStats, boolean archived, String currenyIdentifier) {
		this.clientIdentifier = clientIdentifier;
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
		this.currenyIdentifier = currenyIdentifier;
	}	
	
}
