package com.expenser.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUploadDTO {

	String fileIdentifier;
	String accountIdentifier;
	AccountDTO account;
	ClientDTO client;
	FileMetaDataDTO fileMetaData;
	List<List<String>> dataList;
	
}
