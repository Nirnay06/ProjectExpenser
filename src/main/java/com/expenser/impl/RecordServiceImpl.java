package com.expenser.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.expenser.Entity.Client;
import com.expenser.Entity.RecordLabel;
import com.expenser.Entity.RecordUserCategory;
import com.expenser.Entity.UserAccount;
import com.expenser.Entity.UserCurrency;
import com.expenser.Entity.UserRecord;
import com.expenser.api.AccountService;
import com.expenser.api.ClientService;
import com.expenser.api.RecordLabelService;
import com.expenser.api.RecordService;
import com.expenser.api.RecordUserCategoryService;
import com.expenser.api.UserCurrencyService;
import com.expenser.api.UserService;
import com.expenser.controller.AuthRestController;
import com.expenser.exception.BusinessException;
import com.expenser.model.ClientDTO;
import com.expenser.model.RecordDTO;
import com.expenser.repository.RecordRepository;
import com.expenser.util.DateUtil;

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
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	RecordLabelService recordLabelService;
	
	@Autowired
	private RecordUserCategoryService recordUserCategoryService;
	
	Logger logger = LogManager.getLogger(RecordServiceImpl.class);
	

	@Override
	public UserRecord getEnrichRecordEntityFromDTO(RecordDTO recordDTO) {
		UserRecord record = modelMapper.map(recordDTO, UserRecord.class);
		if(record ==null) {
			record = new UserRecord();
		}
		if(recordDTO !=null) {
			if(recordDTO.getClientIdentifier()!=null) {				
				Client client = clientService.findByClientIdentifier(recordDTO.getClientIdentifier());
				record.setClient(client);
			}
			if(recordDTO.getAccountIdentifier()!=null) {				
				UserAccount account = accountService.findByIdentifier(recordDTO.getAccountIdentifier());
				record.setAccount(account);
			}
			if(recordDTO.getCurrencyIdentifier()!=null) {
				UserCurrency currency = userCurrencyService.findByIdentifier(recordDTO.getCurrencyIdentifier());
				record.setCurrency(currency);
			}
			if(recordDTO.getCategoryIdentifier()!=null) {
				RecordUserCategory category = recordUserCategoryService.findByIdentifier(recordDTO.getCategoryIdentifier());
				record.setCategory(category);
			}if(recordDTO.getRecordDate()!=null && recordDTO.getRecordTime()!=null) {
				try {
					Date recordDate = DateUtil.getDateFromDateAndTimeString(recordDTO.getRecordDate(), recordDTO.getRecordTime());
					record.setDate(recordDate);
				} catch (ParseException e) {
					e.printStackTrace();
					logger.error(e.getStackTrace());
				}
			}
			recordLabelService.setRecordLabelFromRecordDTO(recordDTO, record);
			if(record.getLocation()!=null && record.getLocation().getTitle()!=null) {
				record.getLocation().setRecord(record);
			}
			return record;
		}
		return null;
	}

	@Override
	public UserRecord findByRecordIdentifier(String recordIdentifier) {
		return recordRepository.findByIdentifier(recordIdentifier);
	}

	private void validate(RecordDTO recordDTO, ClientDTO clientDTO) {
		
	}
	
	@Override
	@Transactional
	public UserRecord addUserRecord(RecordDTO recordDTO, ClientDTO clientDTO) throws BusinessException {
		validate(recordDTO, clientDTO);
		UserRecord record = getEnrichRecordEntityFromDTO(recordDTO);
		return recordRepository.save(record);
	}
	
}
