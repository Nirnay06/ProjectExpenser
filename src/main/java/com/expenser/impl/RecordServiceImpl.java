package com.expenser.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenser.Entity.User;
import com.expenser.Entity.UserAccount;
import com.expenser.Entity.UserCurrency;
import com.expenser.Entity.UserRecord;
import com.expenser.api.AccountService;
import com.expenser.api.RecordService;
import com.expenser.api.UserCurrencyService;
import com.expenser.api.UserService;
import com.expenser.model.RecordDTO;
import com.expenser.repository.RecordRepository;

@Service
public class RecordServiceImpl implements RecordService{

	@Autowired
	RecordRepository recordRepository;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserCurrencyService userCurrencyService;
	
	@Override
	public UserRecord addUserRecord(RecordDTO recordDTO) {
		
		return null;
	}
	
	@Override
	public UserRecord getRecordEntityFromDTO(RecordDTO recordDTO) {
		UserRecord record = modelMapper.map(recordDTO, UserRecord.class);
		if(record ==null) {
			record = new UserRecord();
		}
		if(recordDTO !=null) {
			if(recordDTO.getUserIdentifier()!=null) {				
				User user = userService.findByUserIdentifier(recordDTO.getUserIdentifier());
				record.setUser(user);
			}
			if(recordDTO.getAccountIdentifier()!=null) {				
				UserAccount account = accountService.findByIdentifier(recordDTO.getAccountIdentifier());
				record.setAccount(account);
			}
			if(recordDTO.getCurrencyIdentifer()!=null) {
				UserCurrency currency = userCurrencyService.findByIdentifier(recordDTO.getCurrencyIdentifer());
				record.setCurrency(currency);
			}
			if(recordDTO.getRecordCategoryIdentifier()!=null) {
				
				record.setCurrency(currency);
			}
		}
		return null;
	}

	@Override
	public UserRecord findByRecordIdentifier(String recordIdentifier) {
		return recordRepository.findByIdentifier(recordIdentifier);
	}
	
}
