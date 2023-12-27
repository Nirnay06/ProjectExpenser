package com.expenser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expenser.Entity.ExpenserCurrency;
import com.expenser.model.ClientDTO;

@Service
public interface MasterCurrencyService {

	public List<ExpenserCurrency> getAllMasterCurrency();

	public List<ExpenserCurrency> getAllMasterCurrencyNotAssignedToUser(ClientDTO client);
}
