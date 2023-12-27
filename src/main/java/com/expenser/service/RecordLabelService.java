package com.expenser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expenser.Entity.RecordLabel;
import com.expenser.Entity.UserRecord;
import com.expenser.model.RecordDTO;

@Service
public interface RecordLabelService {

	void setRecordLabelFromRecordDTO(RecordDTO recordDTO, UserRecord record);

}
