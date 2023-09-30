package com.expenser.api;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

import com.expenser.exception.BusinessException;
import com.expenser.model.ClientDTO;
import com.expenser.model.CurrencyDTO;
import com.expenser.model.RecordDTO;

public interface DashboardService {

	List<RecordDTO> fetchStatsForLastRecordCard(ClientDTO client, String accountIdentifier, String startDate,
			String endDate) throws AccessDeniedException, BusinessException;

	Map<String, Object> fetchStatsForCashFlowCard(ClientDTO client, String accountIdentifier, String startDate,
			String endDate) throws AccessDeniedException, BusinessException;

	List<Map<String, Object>> fetchStatsForCurrencyBalanceCard(ClientDTO client, String accountIdentifier, String startDate,
			String endDate) throws AccessDeniedException, BusinessException;

	Map<String, Object> fetchStatsForDashboardGaugeCard(ClientDTO client, String accountIdentifier, String startDate,
			String endDate) throws AccessDeniedException, BusinessException;

	Map<String, Object> fetchStatsForExpenseStructureCard(ClientDTO client, String accountIdentifier,
			String startDate, String endDate) throws AccessDeniedException, BusinessException;
}
