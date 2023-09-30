package com.expenser.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountStatsDTO {

	private LocalDate intervalStartdate;
	private LocalDate intervalEnddate;
	private Long balanceAsOfDate;
	private Long totalCreditInInterval;
	private Long totalDebitInInterval;
	private String accountidentifier;
	
	public AccountStatsDTO(LocalDate intervalStartdate, LocalDate intervalEnddate, Long balanceAsOfDate,
			Long totalCreditInInterval, Long totalDebitInInterval) {
		this.intervalStartdate = intervalStartdate;
		this.intervalEnddate = intervalEnddate;
		this.balanceAsOfDate = balanceAsOfDate;
		this.totalCreditInInterval = totalCreditInInterval;
		this.totalDebitInInterval = totalDebitInInterval;
	}
	
	
	
}
