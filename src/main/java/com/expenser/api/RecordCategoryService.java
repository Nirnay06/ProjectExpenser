package com.expenser.api;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expenser.Entity.RecordUserCategory;

@Service
public interface RecordCategoryService {

	RecordUserCategory findRecordCategoryByIdentifier(String categoryIdentifier);
	
	RecordUserCategory findRecordCategoryByRecord(String recordIdentifier);
	
	List<RecordUserCategory> findRecordByUser(String userIdentifier);
	
}
