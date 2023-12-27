package com.expenser.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.expenser.Entity.UserCurrency;
import com.expenser.exception.BusinessException;
import com.expenser.model.ClientDTO;
import com.expenser.model.CurrencyDTO;

@Service
public interface UserCurrencyService {
	
	UserCurrency findByIdentifier(String currencyIdentifier);
	List<CurrencyDTO> fetchActiveCurrenciesByClient(String clientIdentifier);
	Map<String, CurrencyDTO> fetchCurrencyMapByClient(String clientIdentifier);
	void addUserCurrency(CurrencyDTO userCurrencyDTO, ClientDTO client) throws BusinessException;
	UserCurrency findByMasterCurrencyAndClient(String masterCurrencyIdentifier, String clientIdentifier);
	void updateUserCurrency(CurrencyDTO userCurrencyDTO, ClientDTO client) throws BusinessException;
	void deleteUserCurrency(String currencyIdentifier, ClientDTO client) throws BusinessException;
}
