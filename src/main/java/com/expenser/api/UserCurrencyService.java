package com.expenser.api;

import org.springframework.stereotype.Service;

import com.expenser.Entity.UserCurrency;

@Service
public interface UserCurrencyService {
	
	UserCurrency findByIdentifier(String currencyIdentifier);
}
