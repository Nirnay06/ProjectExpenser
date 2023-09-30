package com.expenser.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.expenser.Entity.UserCurrency;
import com.expenser.api.UserCurrencyService;
import com.expenser.model.CurrencyDTO;
import com.expenser.repository.UserCurrencyRepository;

@Service
public class UserCurrencyServiceImpl implements UserCurrencyService{

	@Autowired
	UserCurrencyRepository userCurrencyRepository;

	@Override
	public UserCurrency findByIdentifier(String currencyIdentifier) {
		return userCurrencyRepository.findByIdentifier(currencyIdentifier);
	}

	@Cacheable("currencyCache")
	public List<CurrencyDTO> fetchCurrencyListByClient(String clientIdentifier) {
		List<CurrencyDTO> list = userCurrencyRepository.findByClientIdentifier(clientIdentifier);
		return list;
	}
	@Override
	public List<CurrencyDTO> fetchActiveCurrenciesByClient(String clientIdentifier) {
		return fetchCurrencyListByClient(clientIdentifier );
	}
	
	@Override
	@Cacheable("currencyCache")
	public Map<String, CurrencyDTO> fetchCurrencyMapByClient(String clientIdentifier){
		Map<String, CurrencyDTO> result= new HashMap<>();
		List<CurrencyDTO> list = fetchCurrencyListByClient(clientIdentifier);
		for(CurrencyDTO c : list) {
			result.put(c.getIdentifier(), c);
		}
		
		return result;
	}
}
