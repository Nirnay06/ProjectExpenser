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
@Where(clause = "deleted !=1")
@SQLDelete(sql = "update user_currency set deleted=1 where id=?")
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
	
	@Column(name = "currency_title")
	private String title;
	
	@Column(name = "currency_icon")
	private String icon;
	
	@Column(name ="user_currency_rate")
	private long currencyRate;
	
	@Column(name ="IS_RATE_OVERRIDEN")
	private boolean rateOverriden;
	
	@Column(name ="is_base_currency")
	private boolean baseCurrency;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "master_currency_identifier", referencedColumnName = "identifier" )
	private ExpenserCurrency currency;

	public UserCurrency(String identifier, String title, String icon, long currencyRate, boolean rateOverriden,
			boolean baseCurrency, ExpenserCurrency currency, Client client) {
		this.identifier = identifier;
		this.title = title;
		this.icon = icon;
		this.currencyRate = currencyRate;
		this.rateOverriden = rateOverriden;
		this.baseCurrency = baseCurrency;
		this.currency = currency;
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
