package com.expenser.enums;

public enum RecordType {
	Transfer("Transfer"), Expense("Expense"), Income("Income");
	
	public String name; 
	
	 RecordType(String name) {
		this.name= name;
	}
}
