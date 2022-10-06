package com.expenser.model;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.expenser.Entity.RecordLabel;
import com.expenser.Entity.UserRecord;
import com.expenser.enums.RecordType;
import com.expenser.util.DateUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecordDTO {
	
	private String recordIdentifier;
	private String userIdentifier;
	private String accountName;
	private String accountIdentifier;
	private String accountColor;
	private RecordType recordType;
	private String currencyIdentifer;
	private String currencySymbol;
	@NotNull(message = "Amount cannot be blank")
	private double amount;
	private String recordCategoryTitle;
	private String recordCategoryIdentifier;
	private String recordDate;
	private String recordTime;
	@NotNull(message = "Payment type cannot be blank")
	private String paymentType;
	private String paymentStatus;
	private String comments;
	private String payee;
	private List<RecordLabel> labels = new ArrayList<RecordLabel>();
	private double latitude;
	private double longitude;
	private String title;
	
	public RecordDTO() {
		
	}

	public RecordDTO(UserRecord userRecord) {
		this.recordIdentifier = userRecord.getIdentifier();
		this.userIdentifier = userRecord.getUser().getUserIdentifier();
		this.accountName = userRecord.getAccount().getAccountName();
		this.accountIdentifier = userRecord.getAccount().getIdentifier();
		this.accountColor = userRecord.getAccount().getAccountColor();
		this.recordType = userRecord.getRecordType();
		this.currencyIdentifer = userRecord.getCurrency().getIdentifier();
		this.currencySymbol = userRecord.getCurrency().getIcon();
		this.amount = userRecord.getAmount();
		this.recordCategoryTitle = userRecord.getCategory().getTitle();
		this.recordCategoryIdentifier = userRecord.getCategory().getIdentifier();
		this.recordDate = DateUtil.getDateString(userRecord.getDate());
		this.recordTime = DateUtil.getTimeString(userRecord.getDate());
		this.paymentType = userRecord.getPaymentType();
		this.paymentStatus = userRecord.getPaymentStatus();
		this.comments = userRecord.getComments();
		this.payee = userRecord.getPayee();
		this.labels = userRecord.getLabels();
		this.latitude = userRecord.getLocation().getLatitude();
		this.longitude = userRecord.getLocation().getLongitude();
		this.title = userRecord.getLocation().getTitle();
	}
	
}
