package com.expenser.Entity;

import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.expenser.enums.RecordType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name ="user_record")
@Getter
@Setter
@Where(clause = "deleted !=1")
@SQLDelete(sql = "update user_record set deleted=1 where id=?")
public class UserRecord extends AuditEntity {

	@Id
	@SequenceGenerator(name = "userRecordSequence",sequenceName = "USER_RECORD_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "userRecordSequence", strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name ="identifier")
	private String identifier;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="user_identifier")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="account_identifier")
	private UserAccount account;
	
	@Enumerated(EnumType.STRING)
	@Column(name ="record_type")
	private RecordType recordType;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="user_currency_identifier")
	private UserCurrency currency;
	
	@Column(name ="amount")
	private double amount;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="user_category_identifier")
	private RecordUserCategory category;
	
	@Column(name ="record_date")
	private Date date;
	
	@Column(name = "payment_type")
	private String paymentType;
	
	@Column(name ="payment_status")
	private String paymentStatus;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="refund_record_identifier")
	private UserRecord refundTx;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="transfer_transaction_identifier")
	private UserRecord transferTx;
	
	@Column(name ="comments")
	private String comments;
	
	@Column(name ="payee")
	private String payee;
	
	@Transient
	@OneToMany(mappedBy = "record")
	private List<RecordLabel> labels;
	
	@Transient
	@OneToOne(mappedBy = "record")
	private RecordLocation location;
	
	@PrePersist
	public void PrePersist() {
		if(this.identifier==null) {
			this.identifier=UUID.randomUUID().toString();
		}
	}
}
