package com.expenser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenser.Entity.UserAccount;
import com.expenser.api.AccountService;
import com.expenser.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;

	@Override
	public UserAccount findByIdentifier(String accountIdentifier) {
		return accountRepository.findByIdentifier(accountIdentifier);
	}
	
}
