package com.expenser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenser.Entity.UserCurrency;
import com.expenser.api.UserCurrencyService;
import com.expenser.repository.UserCurrencyRepository;

@Service
public class UserCurrencyServiceImpl implements UserCurrencyService{

	@Autowired
	UserCurrencyRepository userCurrencyRepository;

	@Override
	public UserCurrency findByIdentifier(String currencyIdentifier) {
		return userCurrencyRepository.findByIdentifier(currencyIdentifier);
	}
	
	
}
