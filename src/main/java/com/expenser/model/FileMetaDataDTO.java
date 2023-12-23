package com.expenser.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class FileMetaDataDTO {

	private Boolean headerToggle = true;
	private Integer selectedHeaderRow = 1;
	private Integer selectedFirstRow = 2;
	private Integer selectedLastRow = 1;
	private Boolean isIncomeExpenseDiff = false;
	private Integer amountColumn = null;
	private Boolean isFeeApplicable = false;
	private Integer dateColumn = null;
	private Integer currencyColumn = null;
	private Integer payeeColumn = null;
	private Integer noteColumn = null;
	private Integer rowCount =0;
	private Integer columnCount=0;
	private List<String> alphabetList = new ArrayList<>();
	private String fileIdentifier;
	private String accountIdentifier;
}
