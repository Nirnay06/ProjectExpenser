package com.expenser.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="user_currency")
@Getter
@Setter
@Where(clause = "deleted=CAST(0 as boolean)")
@SQLDelete(sql = "update user_currency set deleted=cast(1 as boolean) where id=?")
public class UserCurrency extends AuditEntity  implements Serializable{
	
	private static final long SerialVersionUID = 8127721722221l;
	
	@Id
	@SequenceGenerator(name = "UserCurrencySequence", sequenceName = "USER_CURR_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UserCurrencySequence")
	private long id;

	@Column(name ="identifier")
	private String identifier;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_identifier", referencedColumnName = "client_identifier")
	private Client client;
	
	@Column(name ="user_currency_rate")
	private BigDecimal currencyRate;
	
	@Column(name ="IS_RATE_OVERRIDEN")
	private boolean rateOverriden;
	
	@Column(name ="is_base_currency")
	private boolean baseCurrency;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "master_currency_identifier", referencedColumnName = "identifier" )
	private ExpenserCurrency currency;
	
	@Column(name ="display_order")
	private long displayOrder;

	public UserCurrency( BigDecimal currencyRate, boolean rateOverriden,
			boolean baseCurrency, ExpenserCurrency currency, Client client, long displayOrder) {
		this.currencyRate = currencyRate;
		this.rateOverriden = rateOverriden;
		this.baseCurrency = baseCurrency;
		this.currency = currency;
		this.client = client;
		this.displayOrder = displayOrder;
	}

	public UserCurrency(String identifier,  BigDecimal currencyRate, boolean rateOverriden,
			boolean baseCurrency, ExpenserCurrency currency, Client client, long displayOrder) {
		this.identifier = identifier;
		this.currencyRate = currencyRate;
		this.rateOverriden = rateOverriden;
		this.baseCurrency = baseCurrency;
		this.currency = currency;
		this.displayOrder = displayOrder;
		this.client = client;
	}
	
	public UserCurrency() {
	}
	
	@PrePersist
	public void PrePersist() {
		if(this.identifier==null) {
			this.identifier=UUID.randomUUID().toString();
		}
	}
}
