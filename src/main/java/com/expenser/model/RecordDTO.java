package com.expenser.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.expenser.Entity.RecordLabel;
import com.expenser.Entity.RecordLocation;
import com.expenser.enums.RecordType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordDTO {
	
	// TODO remove DTOs
	
	private String recordIdentifier;
	private UserDTO user;
	private String clientIdentifier;
	private AccountDTO account;
	private String accountIdentifier;
	private RecordType recordType;
	private CurrencyDTO currency;
	private String currencyIdentifier;
	@NotNull(message = "Amount cannot be blank")
	private BigDecimal amount;
	private RecordCategoryDTO recordCategory;
	private String categoryIdentifier;
	private String recordDate;
	private String recordTime;
	@NotNull(message = "Payment type cannot be blank")
	private String paymentType;
	private String paymentStatus;
	private String comments;
	private String payee;
	private List<RecordLabelDTO> labels = new ArrayList<RecordLabelDTO>();
	private RecordLocationDTO location;
	
	public RecordDTO() {
		
	}

	public RecordDTO(String recordIdentifier, String clientIdentifier, String accountIdentifier, RecordType recordType,
			String currencyIdentifier, @NotNull(message = "Amount cannot be blank") BigDecimal amount,
			String categoryIdentifier, String recordDate, String recordTime,
			@NotNull(message = "Payment type cannot be blank") String paymentType, String paymentStatus,
			String comments, String payee, List<RecordLabelDTO> labels, RecordLocationDTO location) {
		super();
		this.recordIdentifier = recordIdentifier;
		this.clientIdentifier = clientIdentifier;
		this.accountIdentifier = accountIdentifier;
		this.recordType = recordType;
		this.currencyIdentifier = currencyIdentifier;
		this.amount = amount;
		this.categoryIdentifier = categoryIdentifier;
		this.recordDate = recordDate;
		this.recordTime = recordTime;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.comments = comments;
		this.payee = payee;
		this.labels = labels;
		this.location = location;
	}
	
}
