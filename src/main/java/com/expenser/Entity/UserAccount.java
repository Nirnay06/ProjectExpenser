package com.expenser.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import com.expenser.enums.AccountType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_accounts")
@Getter
@Setter
@Where(clause = "deleted=CAST(0 as boolean)")
@SQLDelete(sql = "update user_accounts set deleted=cast(1 as boolean) where id=?")
public class UserAccount extends AuditEntity implements Serializable{
	
	private static final long SerialVersionUID = 812772172712l;
	
	@Id
	@SequenceGenerator(name = "UserAccountSequence", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UserAccountSequence")
	private long id;
	
	@ManyToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name = "client_identifier", referencedColumnName = "client_identifier")
	private Client client;

	@Column(name = "account_identifier")
	private String identifier;
	
	@Column(name="is_active")
	private boolean active;
	
	@Enumerated(EnumType.STRING)
	@Column(name="account_type")
	private AccountType accountType;

	@Column(name ="account_name")
	private String accountName;
	
	@Column(name ="account_color")
	private String accountColor;
	
	@Column(name ="ACCOUNT_BALANCE")
	private long accountBalance;
	
	@Column(name ="icon")
	private String icon;
	
	@Column(name = "initial_balance")
	private long initialBalance;
	
	@Column(name = "IS_EXCLUDE_FROM_STATS")
	private boolean excludeFromStats;
	
	@Column(name = "is_archived")
	private boolean archived;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="account_currency", referencedColumnName = "identifier")
	private UserCurrency accountCurrency;
	
	public UserAccount() {
		
	}

	public UserAccount(Client client, String identifier, boolean active, AccountType accountType,
			String accountName, String accountColor, long accountBalance, String icon, long initialBalance,
			boolean excludeFromStats, boolean archived, UserCurrency accountCurrency) {
		this.identifier = identifier;
		this.client = client;
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
	
	@PrePersist
	public void PrePersist() {
		if(this.identifier==null) {
			this.identifier=UUID.randomUUID().toString();
		}
	}
}
