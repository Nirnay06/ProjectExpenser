package com.expenser.enums;

import lombok.Getter;

@Getter
public enum AccountType {
	General("General"), Cash("Cash"), Current_Account("Current Account"), Credit_Card("Credit Card"),
	Account_With_Overdraft("Account with Overdraft"),Saving_Account("Saving Account"),
	Bonus("Bonus"), Insurance("Insurance"), Loan("Loan"), Mortgage("Mortgage");
	
	private String name;
	AccountType(String name) {
		this.name= name;
	}
}
