package com.expenser.api;

import org.springframework.stereotype.Service;

import com.expenser.Entity.UserAccount;

@Service
public interface AccountService {

	UserAccount findByIdentifier(String accountIdentifier);
}
