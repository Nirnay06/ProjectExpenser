package com.expenser.endpoint.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.expenser.endpoint.RecordEndpoint;
import com.expenser.model.RecordDTO;
import com.expenser.service.RecordService;

@Component
public class RecordEndpointImpl implements RecordEndpoint{

	@Autowired
	private RecordService recordService;
	
	
	@Override
	public List<RecordDTO> getAllRecordsByCurrencyandClient(String currencyIdentifier, String clientIdentifier) {
		return recordService.getAllRecordsByCurrencyandClient(currencyIdentifier, clientIdentifier);
	}

}
