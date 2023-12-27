package com.expenser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.expenser.Entity.ExpenserCurrency;
import com.expenser.Entity.UserCurrency;
import com.expenser.Entity.UserRecord;
import com.expenser.endpoint.RecordEndpoint;
import com.expenser.exception.BusinessException;
import com.expenser.model.ClientDTO;
import com.expenser.model.CurrencyDTO;
import com.expenser.model.RecordDTO;
import com.expenser.repository.MasterCurrencyRepository;
import com.expenser.repository.UserCurrencyRepository;
import com.expenser.service.ClientService;
import com.expenser.service.UserCurrencyService;

@Service
public class UserCurrencyServiceImpl implements UserCurrencyService{

	@Autowired
	UserCurrencyRepository userCurrencyRepository;
	
	@Autowired
	MasterCurrencyRepository masterCurrencyRepository;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	RecordEndpoint recordEndpoint;

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
	public UserCurrency findByMasterCurrencyAndClient(String masterCurrencyIdentifier, String clientIdentifier) {
		return userCurrencyRepository.findByMasterCurrencyIdentifier(masterCurrencyIdentifier, clientIdentifier);
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

	private long getNewDisplayOrderForCurrencyByClient(String clientIdentifier) {
		return userCurrencyRepository.findMaxDisplayOrderByClient(clientIdentifier) + 1;
	}
	@Transactional
	@Override
	public void addUserCurrency(CurrencyDTO userCurrencyDTO, ClientDTO client) throws BusinessException {
		ExpenserCurrency masterCurrency = masterCurrencyRepository
				.findByIdentifier(userCurrencyDTO.getMasterCurrencyIdentifier());
		if (masterCurrency != null) {
			UserCurrency userCurrencyExisting = findByMasterCurrencyAndClient(userCurrencyDTO.getMasterCurrencyIdentifier(), client.getClientIdentifier());
			if (userCurrencyExisting == null) {

				UserCurrency userCurrency = new UserCurrency(userCurrencyDTO.getCurrencyRate(),
						userCurrencyDTO.isRateOverriden(), false, masterCurrency,
						clientService.findByClientIdentifier(client.getClientIdentifier()),
						getNewDisplayOrderForCurrencyByClient(client.getClientIdentifier()));
				userCurrencyRepository.save(userCurrency);

			} else {
				throw new BusinessException("Currency is already assigned");
			}
		} else {
			throw new BusinessException("Invalid Currency selected");
		}
	}

	@Override
	public void updateUserCurrency(CurrencyDTO userCurrencyDTO, ClientDTO client) throws BusinessException {
		UserCurrency userCurrencyExisting = findByMasterCurrencyAndClient(userCurrencyDTO.getMasterCurrencyIdentifier(), client.getClientIdentifier());
		if (userCurrencyExisting != null) {
			userCurrencyExisting.setCurrencyRate(userCurrencyDTO.getCurrencyRate());
			userCurrencyRepository.save(userCurrencyExisting);
		} else {
			throw new BusinessException("Currency doesn't exists for the user");
		}
	}

	@Override
	public void deleteUserCurrency(String currencyIdentifier, ClientDTO client) throws BusinessException {
		UserCurrency userCurrency = findByIdentifier(currencyIdentifier);
		if(userCurrency!=null && userCurrency.getClient().getClientIdentifier().equals(client.getClientIdentifier())) {
			List<RecordDTO> records = recordEndpoint.getAllRecordsByCurrencyandClient(currencyIdentifier, client.getClientIdentifier());
			if(CollectionUtils.isEmpty(records)) {				
				userCurrencyRepository.delete(userCurrency);
			}else {
				throw new BusinessException("You cannot remove this currency. This is already linked to Records");
			}
		}else {
			throw new BusinessException("You are not allowed to remove this currency.");
		}
	}
}
