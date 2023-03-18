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
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.expenser.enums.RecordType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="user_records")
@Getter
@Setter
@Where(clause = "deleted !=1")
@SQLDelete(sql = "update user_record set deleted=1 where id=?")
@AllArgsConstructor
@NoArgsConstructor
public class UserRecord extends AuditEntity  implements Serializable{

	@Id
	@SequenceGenerator(name = "userRecordSequence",sequenceName = "USER_RECORD_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "userRecordSequence", strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name ="identifier")
	private String identifier;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="client_identifier", referencedColumnName = "client_identifier")
	private Client client;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="account_identifier", referencedColumnName = "account_identifier")
	private UserAccount account;
	
	@Enumerated(EnumType.STRING)
	@Column(name ="record_type")
	private RecordType recordType;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name ="user_currency_identifier", referencedColumnName = "identifier")
	private UserCurrency currency;
	
	@Column(name ="amount")
	private BigDecimal amount;
	
	@OneToOne(fetch = FetchType.EAGER)
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
	
	@OneToMany(mappedBy = "record", cascade = CascadeType.ALL)
	private List<RecordLabel> labels;
	
	@OneToOne(mappedBy = "record", cascade = CascadeType.ALL)
	private RecordLocation location;
	
	@PrePersist
	public void PrePersist() {
		if(this.identifier==null) {
			this.identifier=UUID.randomUUID().toString();
		}
	}
}
