package com.expenser.impl;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.expenser.Entity.UserAccount;
import com.expenser.Entity.UserCurrency;
import com.expenser.api.AccountService;
import com.expenser.api.ClientService;
import com.expenser.api.UserCurrencyService;
import com.expenser.enums.AccountType;
import com.expenser.exception.BusinessException;
import com.expenser.mapper.AccountMapper;
import com.expenser.model.AccountDTO;
import com.expenser.model.AccountStatsDTO;
import com.expenser.model.ClientDTO;
import com.expenser.model.IntervalBalanceDTO;
import com.expenser.model.RecordDTO;
import com.expenser.model.SearchRequest;
import com.expenser.model.SearchRequest.Order;
import com.expenser.repository.AccountRepository;
import com.expenser.repository.RecordRepository;
import com.expenser.repository.RecordRepositoryCustom;
import com.expenser.util.DateUtil;
import com.expenser.util.SecurityUtils;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	RecordRepositoryCustom recordRepositoryCustom;
	
	@Autowired
	RecordRepository recordRepository;
	
	@Autowired
	UserCurrencyService userCurrencyService;
	
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	ClientService clientService;
	
	@Override
	public UserAccount findByIdentifier(String accountIdentifier) {
		return accountRepository.findByIdentifier(accountIdentifier);
	}
	
	@Cacheable("accountCache")
	private List<AccountDTO> fetchAccountByClient(String clientIdentifier) throws AccessDeniedException, BusinessException {
		if(clientIdentifier!=null) {
			List<AccountDTO> list=  accountRepository.findAllAccountByClientIdentifier(clientIdentifier);
			list = populateBalance(list);
			return list;
		}
		return new ArrayList<>();
	}

	@Cacheable("accountCache")
	private List<AccountDTO> fetchAccountByClientAndStatus(String clientIdentifier, boolean active) throws AccessDeniedException, BusinessException {
		if(clientIdentifier!=null) {
			List<AccountDTO> list=  accountRepository.findAllAccountByClientIdentifierAndStatus(clientIdentifier, active);
			list = populateBalance(list);
			return list;
		}
		return new ArrayList<>();
	}
	
	private List<AccountDTO> populateBalance(List<AccountDTO> list) throws AccessDeniedException, BusinessException {
		for(AccountDTO a : list) {
			a.setAccountBalance(getAccountBalance(a.getIdentifier()));	
			}
		return list;
	}
	
	@Override
	public List<AccountDTO> fetchActiveAccountByClient(String clientIdentifier) throws AccessDeniedException, BusinessException {
		return fetchAccountByClientAndStatus(clientIdentifier, true);
	}
	
	@Override
	public List<AccountDTO> fetchAllAccountByClient(String clientIdentifier) throws AccessDeniedException, BusinessException {
		return fetchAccountByClient(clientIdentifier);
	}

	@Override
	public AccountDTO getAccountByAccountIdentifier(String accountIdentifier) throws AccessDeniedException, BusinessException {
		String clientIdentifier = SecurityUtils.getClientFromSession().getClientIdentifier(); 
		return accountRepository.findAccountByClientAndAccountIdentifier(clientIdentifier, accountIdentifier);
	}

	@Override
	@Cacheable(value = "accountCache")
	public List<IntervalBalanceDTO> getAccountBalanceStatsByInterval(String accountIdentifier, String interval) {
		List<IntervalBalanceDTO> list = new ArrayList<>();
		if(interval.equals("Weekly")) {
			Pair<LocalDate, LocalDate> weekDates = DateUtil.getStartAndEndDateOfWeek(LocalDate.now());
			LocalDate startDate = weekDates.getFirst();
			LocalDate endDate = weekDates.getSecond();
			while(startDate.compareTo(endDate) <= 0 ) {
				AccountStatsDTO stats =  recordRepositoryCustom.findStatsWithinInterval(accountIdentifier, startDate, startDate);
				list.add(new IntervalBalanceDTO("Balance", DateUtil.getDateString(stats.getIntervalStartdate()),stats.getBalanceAsOfDate()));
				list.add(new IntervalBalanceDTO("Debit", DateUtil.getDateString(stats.getIntervalStartdate()),stats.getTotalDebitInInterval()));
				list.add(new IntervalBalanceDTO("Credit", DateUtil.getDateString(stats.getIntervalStartdate()),stats.getTotalCreditInInterval()));
				startDate = startDate.plusDays(1);
			}
		}
		if(interval.equals("Monthly")) {
			Pair<LocalDate, LocalDate> monthDays = DateUtil.getStartAndEndDateOfMonth(LocalDate.now());
			LocalDate startDate = monthDays.getFirst();
			LocalDate endDate = monthDays.getSecond();
			while(startDate.compareTo(endDate) <= 0 ) {
				AccountStatsDTO stats =  recordRepositoryCustom.findStatsWithinInterval(accountIdentifier, startDate, startDate.plusDays(1));
				list.add(new IntervalBalanceDTO("Balance", DateUtil.getDateString(stats.getIntervalStartdate()),stats.getBalanceAsOfDate()));
				list.add(new IntervalBalanceDTO("Debit", DateUtil.getDateString(stats.getIntervalStartdate()),stats.getTotalDebitInInterval()));
				list.add(new IntervalBalanceDTO("Credit", DateUtil.getDateString(stats.getIntervalStartdate()),stats.getTotalCreditInInterval()));
				startDate = startDate.plusDays(2);
			}
		}
		if(interval.equals("Quarterly")) {
			Pair<LocalDate, LocalDate> quarterDays = DateUtil.getStartAndEndDateOfQuarter(LocalDate.now());
			LocalDate startDate = quarterDays.getFirst();
			LocalDate endDate = quarterDays.getSecond();
			while (startDate.compareTo(endDate) <= 0) {
				AccountStatsDTO stats = recordRepositoryCustom.findStatsWithinInterval(accountIdentifier, startDate,
						startDate.plusDays(6));
				list.add(new IntervalBalanceDTO("Balance",
								DateUtil.getDateString(stats.getIntervalStartdate()) + "-"
										+ DateUtil.getDateString(stats.getIntervalEnddate()),
								stats.getBalanceAsOfDate()));
				
				list.add(new IntervalBalanceDTO("Debit",
								DateUtil.getDateString(stats.getIntervalStartdate()) + "-"
										+ DateUtil.getDateString(stats.getIntervalEnddate()),
								stats.getTotalDebitInInterval()));
				
				list.add(new IntervalBalanceDTO("Credit",
						DateUtil.getDateString(stats.getIntervalStartdate()) + "-"
								+ DateUtil.getDateString(stats.getIntervalEnddate()),
						stats.getTotalCreditInInterval()));
				
				startDate = startDate.plusDays(7);
			}
		}
		
		if (interval.equals("Yearly")) {
			Pair<LocalDate, LocalDate> yearDays = DateUtil.getStartAndEndDateOfYear(LocalDate.now());
			LocalDate startDate = yearDays.getFirst();
			LocalDate endDate = yearDays.getSecond();
			while (startDate.compareTo(endDate) <= 0) {
				AccountStatsDTO stats = recordRepositoryCustom.findStatsWithinInterval(accountIdentifier, startDate,
						startDate.with(TemporalAdjusters.lastDayOfMonth()));
				
				list.add(new IntervalBalanceDTO("Balance", DateUtil.getMonthFromDate(stats.getIntervalEnddate()), stats.getBalanceAsOfDate()));

				list.add(new IntervalBalanceDTO("Debit", DateUtil.getMonthFromDate(stats.getIntervalEnddate()), stats.getTotalDebitInInterval()));

				list.add(new IntervalBalanceDTO("Credit", DateUtil.getMonthFromDate(stats.getIntervalEnddate()), stats.getTotalCreditInInterval()));

				startDate = startDate.plusMonths(1);
			}
		}
		
		if (interval.equals("5 Yearly")) {
			Pair<LocalDate, LocalDate> yearDays = DateUtil.getStartAndEndDateOfYear(LocalDate.now());
			LocalDate startDate = yearDays.getFirst().minusYears(5);
			LocalDate endDate = yearDays.getSecond();
			while (startDate.compareTo(endDate) <= 0) {
				AccountStatsDTO stats = recordRepositoryCustom.findStatsWithinInterval(accountIdentifier, startDate,
						startDate.plusMonths(12).withDayOfMonth(31));
				
				list.add(new IntervalBalanceDTO("Balance", DateUtil.getYearFromDate(stats.getIntervalEnddate()), stats.getBalanceAsOfDate()));

				list.add(new IntervalBalanceDTO("Debit", DateUtil.getYearFromDate(stats.getIntervalEnddate()), stats.getTotalDebitInInterval()));

				list.add(new IntervalBalanceDTO("Credit", DateUtil.getYearFromDate(stats.getIntervalEnddate()), stats.getTotalCreditInInterval()));

				startDate = startDate.plusYears(1);
			}
		}
		
		return list;
	}

	@Override
	@Cacheable(value = "accountCache")
	public String getAccountBalanceString(String accountIdentifier) throws AccessDeniedException, BusinessException {
		ClientDTO client = SecurityUtils.getClientFromSession();
		AccountDTO account = getAccountByAccountIdentifier(accountIdentifier);
		if(account!=null) {
			if(client!=null && client.getClientIdentifier().equals(account.getClientIdentifier())) {					
				return recordRepository.findAccountBalanceStringByAccountIdentifier(accountIdentifier);
			}else {
				throw new AccessDeniedException("You are not authorized to access this resource");
			}
		}
		return null;
	}
	
	@Override
	@Cacheable(value = "accountCache")
	public Long getAccountBalance(String accountIdentifier) throws AccessDeniedException, BusinessException {
		ClientDTO client = SecurityUtils.getClientFromSession();
		AccountDTO account = getAccountByAccountIdentifier(accountIdentifier);
		if(account!=null) {
			if(client!=null && client.getClientIdentifier().equals(account.getClientIdentifier())) {					
				return recordRepository.findAccountBalanceByAccountIdentifier(accountIdentifier);
			}else {
				throw new AccessDeniedException("You are not authorized to access this resource");
			}
		}
		return null;
	}

	@Override
	@CacheEvict(value = "accountCache" , key= "#accountDTO.identifier")
	public AccountDTO addOrEditAccount(AccountDTO accountDTO, ClientDTO client) throws BusinessException {
		if(accountDTO.getAccountName()!=null) {
			List<UserAccount> list=  accountRepository.findAllAccountByClientIdentifierAndName(null, accountDTO.getAccountName());
			if(accountDTO.getIdentifier()!=null) {
				list = list.stream().filter(ac -> !ac.getIdentifier().equals(accountDTO.getIdentifier())).collect(Collectors.toList());
			}
			if(!CollectionUtils.isEmpty(list)) {
				throw new BusinessException("Another Account Exists with the same name");
			}
			
			UserAccount ac = null;
			if(accountDTO.getIdentifier()!=null) {
				ac = accountRepository.findByIdentifier(accountDTO.getIdentifier());
			}
			if(ac==null){
				ac = new UserAccount();
				ac.setClient(clientService.findByClientIdentifier(client.getClientIdentifier()));
			}
			ac.setAccountName(accountDTO.getAccountName());
			ac.setInitialBalance(accountDTO.getInitialBalance());
			ac.setAccountColor(accountDTO.getAccountColor());
			ac.setAccountType(accountDTO.getAccountType());
			if(accountDTO.getCurrenyIdentifier()!=null && StringUtils.hasLength(accountDTO.getCurrenyIdentifier())) {
				UserCurrency  currency = userCurrencyService.findByIdentifier(accountDTO.getCurrenyIdentifier());
				ac.setAccountCurrency(currency);
			}else {
				throw new IllegalArgumentException("Invalid Arguements.");
			}
			UserAccount account = accountRepository.save(ac);
			return accountMapper.toDto(account);
		}else {
			throw new IllegalArgumentException("Invalid Arguements.");
		}
	}
	
	@CacheEvict(value = "accountCache" , key= "#accountDTO.identifier")
	@Override
	public void evictAccountFromCacheByIdentifier(String accountIdentifier) {
		//This is just to evict the cache, no code is required.
	}

	
}
