package com.expenser.api;

import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.expenser.Entity.UserRecord;
import com.expenser.exception.BusinessException;
import com.expenser.model.ClientDTO;
import com.expenser.model.FileUploadDTO;
import com.expenser.model.RecordDTO;
import com.expenser.model.SearchRequest;

@Service
public interface RecordService {
	UserRecord addUserRecord(RecordDTO recordDTO, ClientDTO clientDTO) throws BusinessException;
	UserRecord findByRecordIdentifier(String recordIdentifier);
	UserRecord getNewRecordEntityFromDTO(RecordDTO recordDTO);
	List<RecordDTO> getAllRecordsByAccountandClient(String accountIdentifier, String clientIdentifier);
	List<RecordDTO> getAllRecordsByAccountIdentifier(String accountIdentifier);
	UserRecord updateUserRecord(RecordDTO recordDTO, ClientDTO clientDTO) throws BusinessException;
	void deleteUserRecord(String recordIdentifier, ClientDTO clientDTO) throws AccessDeniedException, BusinessException;
	List<RecordDTO> findRecordsFromAccountsByDate(List<String> accountIdentifiers, String startDate, String endDate,
			SearchRequest searchRequest);
	Integer importRecordFromFile(int recordStartIndex, int recordEndIndex, FileUploadDTO fileUploadDTO);
}	
