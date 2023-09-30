package com.expenser.controller;

import java.math.BigDecimal;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenser.api.AccountService;
import com.expenser.api.DashboardService;
import com.expenser.exception.BusinessException;
import com.expenser.model.ClientDTO;
import com.expenser.model.CurrencyDTO;
import com.expenser.model.RecordDTO;
import com.expenser.util.SecurityUtils;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	DashboardService dashboardService;
	
	
	@GetMapping("/statsForLastRecordCard/{accountIdentifier}/{startDate}/{endDate}")
	public ResponseEntity<?> getStatsForLastRecordCard(@PathVariable String accountIdentifier,
			@PathVariable String startDate, @PathVariable String endDate) throws AccessDeniedException, BusinessException {
		if (accountIdentifier != null) {
			ClientDTO client = SecurityUtils.getClientFromSession();
			if(client!=null) {				
				List<RecordDTO> records  = dashboardService.fetchStatsForLastRecordCard(client, accountIdentifier, startDate, endDate);
				return new ResponseEntity<List<RecordDTO>>(records, HttpStatus.OK);
			}
			throw new AccessDeniedException("You are not authorized to access this resource");
		}
		return null;
	}
	
	@GetMapping("/statsForCashFlowCard/{accountIdentifier}/{startDate}/{endDate}")
	public ResponseEntity<?> getStatsForCashFlowCard(@PathVariable String accountIdentifier,
			@PathVariable String startDate, @PathVariable String endDate) throws AccessDeniedException, BusinessException {
		if (accountIdentifier != null) {
			ClientDTO client = SecurityUtils.getClientFromSession();
			if(client!=null) {				
				Map<String, Object> data  = dashboardService.fetchStatsForCashFlowCard(client, accountIdentifier, startDate, endDate);
				return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
			}
			throw new AccessDeniedException("You are not authorized to access this resource");
		}
		return null;
	}

	@GetMapping("/statsForCurrencyBalanceCard/{accountIdentifier}/{startDate}/{endDate}")
	public ResponseEntity<?> getStatsForCurrencyBalanceCard(@PathVariable String accountIdentifier,
			@PathVariable String startDate, @PathVariable String endDate) throws AccessDeniedException, BusinessException {
		if (accountIdentifier != null) {
			ClientDTO client = SecurityUtils.getClientFromSession();
			if(client!=null) {				
				List<Map<String, Object>> data  = dashboardService.fetchStatsForCurrencyBalanceCard(client, accountIdentifier, startDate, endDate);
				return new ResponseEntity<List<Map<String, Object>>>(data, HttpStatus.OK);
			}
			throw new AccessDeniedException("You are not authorized to access this resource");
		}
		return null;
	}
	
	@GetMapping("/statsForDashboardGauge/{accountIdentifier}/{startDate}/{endDate}")
	public ResponseEntity<?> statsForDashboardGauge(@PathVariable String accountIdentifier,
			@PathVariable String startDate, @PathVariable String endDate) throws AccessDeniedException, BusinessException {
		if (accountIdentifier != null) {
			ClientDTO client = SecurityUtils.getClientFromSession();
			if(client!=null) {				
				Map<String, Object> data  = dashboardService.fetchStatsForDashboardGaugeCard(client, accountIdentifier, startDate, endDate);
				return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
			}
			throw new AccessDeniedException("You are not authorized to access this resource");
		}
		return null;
	}
	
	
	@GetMapping("/statsForExpenseStructureCard/{accountIdentifier}/{startDate}/{endDate}")
	public ResponseEntity<?> statsForExpenseStructureCard(@PathVariable String accountIdentifier,
			@PathVariable String startDate, @PathVariable String endDate) throws AccessDeniedException, BusinessException {
		if (accountIdentifier != null) {
			ClientDTO client = SecurityUtils.getClientFromSession();
			if(client!=null) {				
				Map<String, Object> data  = dashboardService.fetchStatsForExpenseStructureCard(client, accountIdentifier, startDate, endDate);
				return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
			}
			throw new AccessDeniedException("You are not authorized to access this resource");
		}
		return null;
	}
	
	
}
