package com.expenser.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.expenser.enums.RecordType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="user_records")
@Getter
@Setter
@Where(clause = "deleted=CAST(0 as boolean)")
@SQLDelete(sql = "update user_record set deleted=cast(1 as boolean) where id=?")
public class UserRecord extends AuditEntity  implements Serializable{

	@Id
	@SequenceGenerator(name = "userRecordSequence",sequenceName = "USER_RECORD_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "userRecordSequence", strategy = GenerationType.SEQUENCE)
	@Column(name ="id")
	private long id;
	
	@Column(name ="identifier")
	@NaturalId
	private String recordIdentifier;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="client_identifier", referencedColumnName = "client_identifier")
	private Client client;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name ="account_identifier", referencedColumnName = "account_identifier")
	private UserAccount account;
	
	@Enumerated(EnumType.STRING)
	@Column(name ="record_type")
	private RecordType recordType;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE )
	@JoinColumn(name ="user_currency_identifier", referencedColumnName = "identifier")
	private UserCurrency currency;
	
	@Column(name ="amount")
	private BigDecimal amount;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name ="user_category_identifier", referencedColumnName = "identifier")
	private RecordUserCategory category;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="record_date")
	private Date date;
	
	@Column(name = "payment_type")
	private String paymentType;
	
	@Column(name ="payment_status")
	private String paymentStatus;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="refund_record_identifier", referencedColumnName = "identifier")
	private UserRecord refundTx;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="transfer_transaction_identifier", referencedColumnName = "identifier")
	private UserRecord transferTx;
	
	@Column(name ="comments")
	private String comments;
	
	@Column(name ="payee")
	private String payee;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "record", cascade = CascadeType.ALL)
	private List<RecordLabel> labels;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "record_location_id")
	private RecordLocation location;
	
	@Transient
	private BigDecimal baseCurrencyAmount;
	
	@PrePersist
	public void PrePersist() {
		if(this.recordIdentifier==null) {
			this.recordIdentifier=UUID.randomUUID().toString();
		}
	}
	
	@PostLoad
	public void postLoad() {
		this.baseCurrencyAmount = getBaseCurrencyAmount(this.amount, this.currency.getCurrencyRate());
	}
	
	public BigDecimal getBaseCurrencyAmount(BigDecimal amount, BigDecimal currencyRate) {
		return amount.multiply(currencyRate);
	}

	public UserRecord(long id, String recordIdentifier, Client client, UserAccount account, RecordType recordType,
			UserCurrency currency, BigDecimal amount, RecordUserCategory category, Date date, String paymentType,
			String paymentStatus, UserRecord refundTx, UserRecord transferTx, 
			String comments, String payee,
			List<RecordLabel> labels, RecordLocation location
			) {
		this.id = id;
		this.recordIdentifier = recordIdentifier;
		this.client = client;
		this.account = account;
		this.recordType = recordType;
		this.currency = currency;
		this.amount = amount;
		this.category = category;
		this.date = date;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.refundTx = refundTx;
		this.transferTx = transferTx;
		this.comments = comments;
		this.payee = payee;
		this.labels = labels;
		this.location = location;
	}

	public UserRecord() {
		
	}
	
}
