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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.AccessDeniedException;
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
import com.expenser.mapper.RecordMapper;
import com.expenser.model.ClientDTO;
import com.expenser.model.RecordDTO;
import com.expenser.model.RecordLabelDTO;
import com.expenser.model.SearchRequest;
import com.expenser.repository.RecordRepository;
import com.expenser.repository.RecordRepositoryCustom;
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
	
	@Autowired
	private RecordMapper recordMapper;
	
	@Autowired 
	private RecordRepositoryCustom recordRepositoryCustom;
	
	Logger logger = LogManager.getLogger(RecordServiceImpl.class);
	

	@Override
	public UserRecord getNewRecordEntityFromDTO(RecordDTO recordDTO) {
		UserRecord record = modelMapper.map(recordDTO, UserRecord.class);
		if(record ==null) {
			record = new UserRecord();
		}
		return updateRecordFromDTO(recordDTO, record);
	}

	private UserRecord updateRecordFromDTO(RecordDTO recordDTO, UserRecord record) {
		recordMapper.updateEntityFromDTO(recordDTO, record);
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
			}
			if(recordDTO.getRecordDate()!=null && recordDTO.getRecordTime()!=null) {
				try {
					Date recordDate = DateUtil.getDateFromDateAndTimeString(recordDTO.getRecordDate(), recordDTO.getRecordTime());
					record.setDate(recordDate);
				} catch (ParseException e) {
					e.printStackTrace();
					logger.error(e.getStackTrace());
				}
			}
			if(!CollectionUtils.isEmpty(recordDTO.getLabels())) {
				recordLabelService.setRecordLabelFromRecordDTO(recordDTO, record);
			}
			return record;
		}
		return record;
	}

	@Override
	public UserRecord findByRecordIdentifier(String recordIdentifier) {
		return recordRepository.findByIdentifier(recordIdentifier);
	}

	private void validate(RecordDTO recordDTO, ClientDTO clientDTO) {
		
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "accountCache" , key= "#recordDTO.accountIdentifier")
	public UserRecord addUserRecord(RecordDTO recordDTO, ClientDTO clientDTO) throws BusinessException {
		validate(recordDTO, clientDTO);
		UserRecord record = getNewRecordEntityFromDTO(recordDTO);
		return recordRepository.save(record);
	}

	@Override
	public List<RecordDTO> getAllRecordsByAccountandClient(String accountIdentifier, String clientIdentifier) {
		List<UserRecord> userRecords = recordRepository.findByAccountIdentifier(accountIdentifier);
		return getDTOsFromEntityList(userRecords);
	}
	
	public RecordDTO getDTOFromEntity(UserRecord record) {
		RecordDTO recordDTO = modelMapper.map(record, RecordDTO.class);
		return recordDTO;
	}
	
	@Cacheable("recordCache")
	public List<RecordDTO> getDTOsFromEntityList(List<UserRecord> records){
		List<RecordDTO> recordDTOs = new ArrayList<>();
		for(UserRecord r : records) {
			recordDTOs.add(recordMapper.toDto(r));
		}
		return recordDTOs;
	}

	@Override
	public List<RecordDTO> getAllRecordsByAccountIdentifier(String accountIdentifier) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "accountCache" , key= "#recordDTO.accountIdentifier")
	public UserRecord updateUserRecord(RecordDTO recordDTO, ClientDTO clientDTO)  throws BusinessException {
		validate(recordDTO, clientDTO);
		UserRecord record = getUpdatedUserRecordFromDTO(recordDTO);
		if(record!=null) {
			return recordRepository.save(record);
		}
		throw new BusinessException("Record doesn't exists");
	}

	private UserRecord getUpdatedUserRecordFromDTO(RecordDTO recordDTO) {
		if(recordDTO != null && recordDTO.getRecordIdentifier()!=null ) {			
			UserRecord record = findByRecordIdentifier(recordDTO.getRecordIdentifier());
			if(record!=null) {
				record = updateRecordFromDTO(recordDTO, record);
				return record;
			}
		}
		return null;
	}

	@Override
	@Transactional
	public void deleteUserRecord(String recordIdentifier, ClientDTO clientDTO) throws AccessDeniedException, BusinessException {
		UserRecord userRecord = recordRepository.findByIdentifier(recordIdentifier);
		if(userRecord!=null) {
			if(clientDTO!=null && clientDTO.getClientIdentifier()!=null && userRecord.getClient().getClientIdentifier().equals(clientDTO.getClientIdentifier())) {
				userRecord.setDeleted(true);
				if(!CollectionUtils.isEmpty(userRecord.getLabels())) {
					for(RecordLabel label : userRecord.getLabels()) {
						label.setDeleted(true);
					}
				}
				if(userRecord.getRefundTx()!=null) {
					userRecord.setRefundTx(null);
				}
				if(userRecord.getTransferTx()!=null) {
					userRecord.setTransferTx(null);
				}
				if(userRecord.getTransferTx()!=null) {
					deleteUserRecord(userRecord.getRefundTx().getRecordIdentifier(), clientDTO);
				}
				recordRepository.save(userRecord);
				accountService.evictAccountFromCacheByIdentifier(userRecord.getAccount().getIdentifier());
			}else {
				throw new AccessDeniedException("You are not Authorized for this action");
			}
		}else {
			throw new BusinessException("Record not found");
		}
	}

	@Override
	public List<RecordDTO> findRecordsFromAccountsByDate(List<String> accountIdentifiers, String startDate,
			String endDate, SearchRequest searchRequest) {
		return recordRepositoryCustom.findRecordsFromAccountsByDate(accountIdentifiers, startDate, endDate, searchRequest);
	}
}
