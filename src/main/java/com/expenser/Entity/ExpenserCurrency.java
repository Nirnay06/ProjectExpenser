package com.expenser.Entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "master_currency")
@Where(clause = "deleted !=1")
@Getter
@Setter
public class ExpenserCurrency extends AuditEntity {
	
	@Id
	@SequenceGenerator(name = "masterCurrencySeq",sequenceName = "MASTER_CURRENCY_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "masterCurrencySeq",strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name = "identifier")
	private String identifier;
	
	@Column(name = "currency_symbol")
	private String currencySymbol;
	
	@Column(name = "current_conversion_rate")
	private double conversionRate;
	
	@Column(name ="last_synced_date")
	private Date syncDate;
	
	public ExpenserCurrency() {
		
	}

	public ExpenserCurrency(String identifier, String currencySymbol, double conversionRate, Date syncDate) {
		this.identifier= identifier;
		this.currencySymbol = currencySymbol;
		this.conversionRate = conversionRate;
		this.syncDate = syncDate;
	}
	
	@PrePersist
	public void PrePersist() {
		if(this.identifier==null) {
			this.identifier=UUID.randomUUID().toString();
		}
	}
}
