package com.expenser.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.expenser.model.AccountStatsDTO;
import com.expenser.model.RecordDTO;
import com.expenser.model.SearchRequest;

@Component
public interface RecordRepositoryCustom {
	public AccountStatsDTO findStatsWithinInterval(String accountIdentifier, LocalDate startDate, LocalDate endDate);

	public List<RecordDTO> findRecordsFromAccountsByDate(List<String> accountIdentifiers, String startDate,
			String endDate, SearchRequest searchRequest);

	public Map<String, Object> findCashFlowForInterval(List<String> accountIdentifiers, LocalDate sDate,
			LocalDate eDate);
	
	public AccountStatsDTO findStatsWithinIntervalForAccountList(List<String> accountIdentifier, LocalDate startDate, LocalDate endDate);
}
