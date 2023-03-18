package com.expenser.api;

import org.springframework.stereotype.Service;

import com.expenser.Entity.UserRecord;
import com.expenser.exception.BusinessException;
import com.expenser.model.ClientDTO;
import com.expenser.model.RecordDTO;

@Service
public interface RecordService {
	UserRecord addUserRecord(RecordDTO recordDTO, ClientDTO clientDTO) throws BusinessException;
	UserRecord findByRecordIdentifier(String recordIdentifier);
	UserRecord getEnrichRecordEntityFromDTO(RecordDTO recordDTO);
}	
