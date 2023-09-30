package com.expenser.api;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.expenser.Entity.UserCurrency;
import com.expenser.model.CurrencyDTO;

@Service
public interface UserCurrencyService {
	
	UserCurrency findByIdentifier(String currencyIdentifier);
	List<CurrencyDTO> fetchActiveCurrenciesByClient(String clientIdentifier);
	Map<String, CurrencyDTO> fetchCurrencyMapByClient(String clientIdentifier);
}
