package com.expenser.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenser.Entity.ExpenserCurrency;
import com.expenser.Entity.UserCurrency;
import com.expenser.model.ClientDTO;
import com.expenser.model.CurrencyDTO;
import com.expenser.repository.MasterCurrencyRepository;
import com.expenser.service.MasterCurrencyService;
import com.expenser.service.UserCurrencyService;

@Service
public class MasterCurrencyServiceImpl implements MasterCurrencyService{

	@Autowired
	private MasterCurrencyRepository masterCurrencyRepository;
	
	@Autowired
	private UserCurrencyService userCurrencyService;
	
	@Override
	public List<ExpenserCurrency> getAllMasterCurrency() {
		return masterCurrencyRepository.findAll();	
	}

	@Override
	public List<ExpenserCurrency> getAllMasterCurrencyNotAssignedToUser(ClientDTO client) {
		List<ExpenserCurrency> masterList = getAllMasterCurrency();
		List<CurrencyDTO> userList = userCurrencyService.fetchActiveCurrenciesByClient(client.getClientIdentifier());
		if(CollectionUtils.isNotEmpty(userList)) {			
			List<String> identifiers = userList.stream().map(currency -> currency.getMasterCurrencyIdentifier()).collect(Collectors.toList());
			masterList.stream().filter( currency -> identifiers.contains(currency.getIdentifier())).collect(Collectors.toList());
		}
		return masterList;
	}

}
