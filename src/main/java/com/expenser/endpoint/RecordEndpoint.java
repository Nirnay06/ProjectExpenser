package com.expenser.endpoint;

import java.util.List;

import org.springframework.stereotype.Component;

import com.expenser.model.RecordDTO;

@Component
public interface RecordEndpoint {
	List<RecordDTO> getAllRecordsByCurrencyandClient(String currencyIdentifier, String clientIdentifier);
}
