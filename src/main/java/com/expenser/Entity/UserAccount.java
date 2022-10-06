package com.expenser.Entity;

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
@Where(clause = "deleted !=1")
@SQLDelete(sql = "update user_accounts set deleted=1 where id=?")
public class UserAccount extends AuditEntity{
	
	@Id
	@SequenceGenerator(name = "UserAccountSequence", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "UserAccountSequence")
	private long id;
	
	@ManyToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name = "user_identifier")
	private User user;
	
	@Column(name = "identifier")
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
	
	@Column(name ="balance")
	private double accountBalance;
	
	@Column(name ="icon")
	private String icon;
	
	@Column(name = "initial_balance")
	private double initialBalance;
	
	@Column(name = "is_exlcude_from_stats")
	private boolean excludeFromStats;
	
	@Column(name = "is_archived")
	private boolean archived;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="account_currency")
	private UserCurrency accountCurrency;
	
	public UserAccount() {
		
	}

	public UserAccount(User user, String identifier, boolean active, AccountType accountType,
			String accountName, String accountColor, double accountBalance, String icon, double initialBalance,
			boolean excludeFromStats, boolean archived, UserCurrency accountCurrency) {
		this.identifier = identifier;
		this.user = user;
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
