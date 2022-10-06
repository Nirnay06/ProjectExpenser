package com.expenser.api;

import org.springframework.stereotype.Service;

import com.expenser.Entity.UserRecord;
import com.expenser.model.RecordDTO;

@Service
public interface RecordService {
	UserRecord addUserRecord(RecordDTO recordDTO);
	UserRecord getRecordEntityFromDTO(RecordDTO recordDTO);
	UserRecord findByRecordIdentifier(String recordIdentifier);
}	
