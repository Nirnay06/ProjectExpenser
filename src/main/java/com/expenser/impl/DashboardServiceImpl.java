package com.expenser.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.AccessDeniedException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.expenser.Entity.UserCurrency;
import com.expenser.api.AccountService;
import com.expenser.api.DashboardService;
import com.expenser.api.RecordService;
import com.expenser.api.UserCurrencyService;
import com.expenser.exception.BusinessException;
import com.expenser.model.AccountDTO;
import com.expenser.model.AccountStatsDTO;
import com.expenser.model.ClientDTO;
import com.expenser.model.CurrencyDTO;
import com.expenser.model.RecordDTO;
import com.expenser.model.SearchRequest;
import com.expenser.model.SearchRequest.Order;
import com.expenser.repository.RecordRepository;
import com.expenser.repository.RecordRepositoryCustom;

@Service
public class DashboardServiceImpl implements DashboardService{

	private static final BigDecimal HUNDRED = new BigDecimal(100);

	@Autowired
	AccountService accountService;
	
	@Autowired
	RecordService recordService;
	
	@Autowired
	RecordRepositoryCustom recordRepositoryCustom;
	
	@Autowired
	RecordRepository recordRepository;
	
	@Autowired
	UserCurrencyService userCurrencyService;
	
	
	@Override
	public List<RecordDTO> fetchStatsForLastRecordCard(ClientDTO client, String accountIdentifier, String startDate,
			String endDate) throws AccessDeniedException, BusinessException {
		List<String> accountIdentifiers = getAccountListForCharts(client, accountIdentifier);
		if(!CollectionUtils.isEmpty(accountIdentifiers)) {
			SearchRequest searchRequest = new SearchRequest();
			searchRequest.setPageSize(4);
			searchRequest.addOrderBy("record_date", Order.DESC);
			List<RecordDTO> records = recordService.findRecordsFromAccountsByDate(accountIdentifiers, startDate, endDate, searchRequest);
			return records;
		}
		return new ArrayList();
	}

	private List<String> getAccountListForCharts(ClientDTO client, String accountIdentifier)
			throws AccessDeniedException, BusinessException {
		List<String> accountIdentifiers = new ArrayList<String>();
		if(accountIdentifier.equals("ALL")) {
			List<AccountDTO> accounts = accountService.fetchActiveAccountByClient(client.getClientIdentifier());
			if(!CollectionUtils.isEmpty(accounts)) {
				accountIdentifiers = accounts.stream().filter(a -> !a.isExcludeFromStats() && !a.isArchived()).map(a -> a.getIdentifier()).collect(Collectors.toList());
			}
		}else {
			AccountDTO account = accountService.getAccountByAccountIdentifier(accountIdentifier);
			if(account !=null && account.getClientIdentifier().equals(client.getClientIdentifier())) {
				if(!account.isArchived() && !account.isExcludeFromStats() && account.isActive()) {
					accountIdentifiers.add(accountIdentifier);
				}
			}
		}
		return accountIdentifiers;
	}

	@Override
	public Map<String, Object> fetchStatsForCashFlowCard(ClientDTO client, String accountIdentifier, String startDate,
			String endDate) throws AccessDeniedException, BusinessException {
		Map<String, Object> map = new HashMap<>();
	    LocalDate sDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	    LocalDate eDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		Period period = Period.between(sDate, eDate);
		
		LocalDate previousStartDate = sDate.minus(period);
        LocalDate previousEndDate = eDate.minus(period);
        
        List<String> accountIdentifiers = getAccountListForCharts(client, accountIdentifier);
		if(!CollectionUtils.isEmpty(accountIdentifiers)) {
			Map<String, Object> currentDetails  = getCashFlowForPeriod(accountIdentifiers,sDate, eDate);
			Map<String, Object> prevDetails  = getCashFlowForPeriod(accountIdentifiers,previousStartDate, previousEndDate);
			BigDecimal currentExpense =new BigDecimal((currentDetails.get("Expense") !=null ?currentDetails.get("Expense").toString() :"0"));
			BigDecimal currentIncome=new BigDecimal((currentDetails.get("Income") !=null ?currentDetails.get("Income").toString() :"0"));
			BigDecimal prevIncome =new BigDecimal((prevDetails.get("Expense") !=null ?prevDetails.get("Expense").toString()  :"0"));
			BigDecimal prevExpense =new BigDecimal((prevDetails.get("Income") !=null ?prevDetails.get("Income").toString()  :"0"));
			
			map.put("CURRENT_EXPENSE", currentExpense );
			map.put("CURRENT_INCOME", currentIncome);
			map.put("TOTAL", currentExpense.add(currentIncome));
			
			if(!currentExpense.equals(BigDecimal.ZERO) || !currentIncome.equals(BigDecimal.ZERO)) {				
				map.put("CURRENT_EXPENSE_PERCENT", currentExpense.abs().divide(currentExpense.abs().max(currentIncome), 2, RoundingMode.HALF_UP).multiply(HUNDRED));
				map.put("CURRENT_INCOME_PERCENT",  currentIncome.abs().divide(currentExpense.abs().max(currentIncome), 2, RoundingMode.HALF_UP).multiply(HUNDRED));
			}else {
				map.put("CURRENT_EXPENSE_PERCENT",0);
				map.put("CURRENT_INCOME_PERCENT", 0);
			}
			if(!prevIncome.equals(BigDecimal.ZERO) || !prevExpense.equals(BigDecimal.ZERO)) {				
				map.put("VS_PREVIOUS", (currentExpense.add(currentIncome).subtract((prevIncome.add(prevExpense)))).divide(prevIncome.add(prevExpense), 2, RoundingMode.HALF_UP).multiply(HUNDRED));
			}
			
		}
       return map;
	}

	private Map<String, Object> getCashFlowForPeriod(List<String> accountIdentifiers, LocalDate sDate, LocalDate eDate) {
			Map<String, Object> map = recordRepositoryCustom.findCashFlowForInterval(accountIdentifiers, sDate, eDate);
			return map;
	}

	@Override
	public List<Map<String, Object>> fetchStatsForCurrencyBalanceCard(ClientDTO client, String accountIdentifier,
			String startDate, String endDate) throws AccessDeniedException, BusinessException {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        List<String> accountIdentifiers = getAccountListForCharts(client, accountIdentifier);
        LocalDate sDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	    LocalDate eDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		if (!CollectionUtils.isEmpty(accountIdentifiers)) {
			List<String[]> resultList = recordRepository.findCurrencyBalanceForInterval(accountIdentifiers, Date.valueOf(sDate),
					Date.valueOf(eDate));
			if(!CollectionUtils.isEmpty(resultList)) {
				double maxBalance = Double.NEGATIVE_INFINITY;
				Map<String, BigDecimal> baseAmountByCurrency = new HashMap<String, BigDecimal>();
				Map<String, BigDecimal> amountByCurrency = new HashMap<String, BigDecimal>();
				Map<String, CurrencyDTO> currencyList = userCurrencyService.fetchCurrencyMapByClient(client.getClientIdentifier());
				for(String[] c :resultList) {
					if(currencyList.containsKey(c[0])) {						
						BigDecimal baseValue = new BigDecimal(c[2]);
						baseAmountByCurrency.put((String) c[0],baseValue);
						amountByCurrency.put((String) c[0],new BigDecimal(c[1]));
						if(maxBalance < baseValue.doubleValue() && baseValue.doubleValue() !=0) {
							maxBalance = baseValue.doubleValue();
						}
					}else {
						throw new BusinessException("Something Went Wrong");
					}
				}
				for(CurrencyDTO currency :currencyList.values()) {
					if (amountByCurrency.containsKey(currency.getIdentifier())) {
						String identifier = currency.getIdentifier();
						BigDecimal percentage = BigDecimal.ZERO;
						if (baseAmountByCurrency.containsKey(identifier)) {
							percentage = baseAmountByCurrency.get(identifier).divide(new BigDecimal(maxBalance), 2,  RoundingMode.HALF_UP)
									.multiply(HUNDRED);
							if(percentage.doubleValue() < 0 || baseAmountByCurrency.get(identifier).doubleValue() < 0) {
								percentage = BigDecimal.ZERO;
							}else if(percentage.doubleValue() > 100) {
								percentage = HUNDRED;
							}
						}
						Map<String, Object> details = new HashMap<String, Object>();
						details.put("CURRENCY_ICON", currency.getIcon());
						details.put("CURRENCY_TITLE", currency.getTitle());
						details.put("AMOUNT", amountByCurrency.get(currency.getIdentifier()));
						details.put("PERCENTAGE", percentage);
						details.put("IDENTIFIER", identifier);
						result.add(details);
					}
				}
			}
		}
		return result;
	}
	
	@Override
	public Map<String, Object> fetchStatsForDashboardGaugeCard(ClientDTO client, String accountIdentifier, String startDate,
			String endDate) throws AccessDeniedException, BusinessException {
		Map<String, Object> map = new HashMap<>();
	    LocalDate sDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	    LocalDate eDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		Period period = Period.between(sDate, eDate);
		
		LocalDate previousStartDate = sDate.minus(period);
        LocalDate previousEndDate = eDate.minus(period);
        
        List<String> accountIdentifiers = getAccountListForCharts(client, accountIdentifier);
		if(!CollectionUtils.isEmpty(accountIdentifiers)) {
			AccountStatsDTO currentStats =  recordRepositoryCustom.findStatsWithinIntervalForAccountList(accountIdentifiers, sDate, eDate);
			AccountStatsDTO prevStats =  recordRepositoryCustom.findStatsWithinIntervalForAccountList(accountIdentifiers, previousStartDate, previousEndDate);
			
		
			BigDecimal balancePercentage  = BigDecimal.ZERO;
			BigDecimal cashPercentage  = BigDecimal.ZERO;
			BigDecimal spendPercentage  = BigDecimal.ZERO;
			long balanceAsOfDate =0l,cashFlowAsOfDate=0l,spendAsOfDate=0l;
			
			if (currentStats != null && prevStats != null) {

				if (prevStats.getBalanceAsOfDate() == 0) {
					balancePercentage = BigDecimal.ONE;
				} else {
					BigDecimal currentBalance = new BigDecimal(currentStats.getBalanceAsOfDate());
					BigDecimal prevBalance = new BigDecimal(prevStats.getBalanceAsOfDate());
					balancePercentage = currentBalance.subtract(prevBalance).divide(prevBalance.abs(), 2,
							RoundingMode.HALF_UP);

				}

				if (prevStats.getTotalDebitInInterval() == 0) {
					spendPercentage = BigDecimal.ONE;
				} else {
					BigDecimal currentSpend = new BigDecimal(currentStats.getTotalDebitInInterval());
					BigDecimal prevSpend = new BigDecimal(prevStats.getTotalDebitInInterval());
					spendPercentage = currentSpend.subtract(prevSpend).divide(prevSpend.abs(), 2, RoundingMode.HALF_UP);

				}

				if (prevStats.getTotalDebitInInterval() + prevStats.getTotalCreditInInterval() == 0) {
					spendPercentage = BigDecimal.ONE;
				} else {
					BigDecimal currentFlow = new BigDecimal(
							currentStats.getTotalCreditInInterval() + currentStats.getTotalDebitInInterval());
					BigDecimal prevFLow = new BigDecimal(
							prevStats.getTotalDebitInInterval() + prevStats.getTotalCreditInInterval());
					cashPercentage = currentFlow.subtract(prevFLow).divide(prevFLow.abs(), 2, RoundingMode.HALF_UP);

				}

				if (balancePercentage.compareTo(BigDecimal.ONE) == 1) {
					balancePercentage = BigDecimal.ONE;
				}
				if (spendPercentage.compareTo(BigDecimal.ONE) == 1) {
					spendPercentage = BigDecimal.ONE;
				}
				if (cashPercentage.compareTo(BigDecimal.ONE) == 1) {
					cashPercentage = BigDecimal.ONE;
				}
				balanceAsOfDate = currentStats.getBalanceAsOfDate();
				cashFlowAsOfDate = currentStats.getTotalCreditInInterval() + currentStats.getTotalDebitInInterval();
				spendAsOfDate = currentStats.getTotalDebitInInterval();

			}
			map.put("BALANCE_PERCENTAGE", balancePercentage);
			map.put("CASH_PERCENTAGE", cashPercentage);
			map.put("SPEND_PERCENTAGE", spendPercentage);
					
			map.put("CURRENT_BALANCE", balanceAsOfDate);
			map.put("CURRENT_CASH_FLOW",cashFlowAsOfDate );
			map.put("CURRENT_SPEND", spendAsOfDate);
			
		}
       return map;
	}
	
	@Override
	public Map<String, Object> fetchStatsForExpenseStructureCard(ClientDTO client, String accountIdentifier,
			String startDate, String endDate) throws AccessDeniedException, BusinessException {
		Map<String, Object> response = new HashMap<String, Object>();
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        List<String> accountIdentifiers = getAccountListForCharts(client, accountIdentifier);
        LocalDate sDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	    LocalDate eDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
	    Period period = Period.between(sDate, eDate);
		
		LocalDate previousStartDate = sDate.minus(period);
        LocalDate previousEndDate = eDate.minus(period);
		if (!CollectionUtils.isEmpty(accountIdentifiers)) {
			AccountStatsDTO currentStats =  recordRepositoryCustom.findStatsWithinIntervalForAccountList(accountIdentifiers, sDate, eDate);
			AccountStatsDTO prevStats =  recordRepositoryCustom.findStatsWithinIntervalForAccountList(accountIdentifiers, previousStartDate, previousEndDate);
			BigDecimal totalCurrentExpense = BigDecimal.ZERO;
			BigDecimal percentageChange = BigDecimal.ZERO;
			
			if(currentStats !=null && prevStats !=null) {
				totalCurrentExpense = new BigDecimal(currentStats.getTotalDebitInInterval());
				BigDecimal totalPrevExpense  = new BigDecimal(prevStats.getTotalDebitInInterval());
				percentageChange = totalCurrentExpense.subtract(totalPrevExpense).divide(totalPrevExpense.abs(), 2, RoundingMode.HALF_UP).multiply(HUNDRED);
			}
			List<String[]> resultList = recordRepository.findSpendByCategoryForInterval(accountIdentifiers, Date.valueOf(sDate),
					Date.valueOf(eDate));
			if(!CollectionUtils.isEmpty(resultList)) {
				for(String[] c :resultList) {
					Map<String, Object> details = new HashMap<String, Object>();
					details.put("category", c[0]);
					BigDecimal value = new BigDecimal(c[1]);
					details.put("value", value);
					details.put("PERCENTAGE", value.divide(totalCurrentExpense, 2, RoundingMode.HALF_UP).multiply(HUNDRED));
					result.add(details);
					
				}
				
			}
			response.put("data", result);
			response.put("TOTAL_EXPENSE", totalCurrentExpense);
			response.put("PERCENTAGE_CHANGE", percentageChange);
		}
		return response;
	}
}
