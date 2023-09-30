package com.expenser.api;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.expenser.Entity.UserAccount;
import com.expenser.exception.BusinessException;
import com.expenser.model.AccountDTO;
import com.expenser.model.ClientDTO;
import com.expenser.model.IntervalBalanceDTO;
import com.expenser.model.RecordDTO;

@Service
public interface AccountService {

	UserAccount findByIdentifier(String accountIdentifier);

	List<AccountDTO> fetchActiveAccountByClient(String clientIdentifier) throws AccessDeniedException, BusinessException;

	List<AccountDTO> fetchAllAccountByClient(String clientIdentifier) throws AccessDeniedException, BusinessException;

	AccountDTO getAccountByAccountIdentifier(String accountIdentifier) throws AccessDeniedException, BusinessException;

	List<IntervalBalanceDTO> getAccountBalanceStatsByInterval(String accountIdentifier, String interval);

	Long getAccountBalance(String accountIdentifier) throws AccessDeniedException, BusinessException;

	AccountDTO addOrEditAccount(AccountDTO accountDTO, ClientDTO client) throws BusinessException;

	void evictAccountFromCacheByIdentifier(String accountIdentifier);

	String getAccountBalanceString(String accountIdentifier) throws AccessDeniedException, BusinessException;

	

}
