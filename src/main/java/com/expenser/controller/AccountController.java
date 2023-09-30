package com.expenser.controller;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expenser.api.AccountService;
import com.expenser.api.RecordService;
import com.expenser.exception.BusinessException;
import com.expenser.model.AccountDTO;
import com.expenser.model.ClientDTO;
import com.expenser.model.IntervalBalanceDTO;
import com.expenser.model.RecordDTO;
import com.expenser.util.SecurityUtils;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@Autowired
	RecordService recordService;
	
	
	@GetMapping("/getActiveClientAccount")
	public ResponseEntity<?> fetchActiveAccountByClient() throws AccessDeniedException, BusinessException{
		ClientDTO client = SecurityUtils.getClientFromSession();
		if(client !=null) {
			List<AccountDTO> accountList = accountService.fetchActiveAccountByClient(client.getClientIdentifier());
			return new ResponseEntity<List<AccountDTO>>(accountList, HttpStatus.OK);
		}
		throw new AccessDeniedException(null);
	}
	
	@GetMapping("/allUserBankAccount")
	public ResponseEntity<?> fetchAllAccountByClient() throws AccessDeniedException, BusinessException{
		ClientDTO client = SecurityUtils.getClientFromSession();
		if(client !=null) {
			List<AccountDTO> accountList = accountService.fetchAllAccountByClient(client.getClientIdentifier());
			return new ResponseEntity<List<AccountDTO>>(accountList, HttpStatus.OK);
		}
		throw new AccessDeniedException(null);
	}
	
	@GetMapping("/details/{accountIdentifier}")
	public ResponseEntity<?> getAccountDetailsByIdentifier(@PathVariable String accountIdentifier) throws AccessDeniedException, BusinessException{
		if(accountIdentifier !=null) {
			AccountDTO account = accountService.getAccountByAccountIdentifier(accountIdentifier);
			return new ResponseEntity<AccountDTO>(account, HttpStatus.OK);
		}
		return null;
	}
	
	@GetMapping("/records/{accountIdentifier}")
	public ResponseEntity<?> getAllRecordsByAccountIdentifier(@PathVariable String accountIdentifier) throws AccessDeniedException, BusinessException{
		if(accountIdentifier !=null) {
			AccountDTO account = accountService.getAccountByAccountIdentifier(accountIdentifier);
			if(account!=null) {
				List<RecordDTO> recordList = recordService.getAllRecordsByAccountandClient(accountIdentifier, account.getClientIdentifier());
				return new ResponseEntity<List<RecordDTO>>(recordList, HttpStatus.OK);
			}
		}
		return null;
	}
	
	@GetMapping("/balanceData/{accountIdentifier}/{interval}")
	public ResponseEntity<?> getBalanceDataForChart(@PathVariable String accountIdentifier, @PathVariable String interval) throws AccessDeniedException, BusinessException{
		ClientDTO client = SecurityUtils.getClientFromSession();
		if(accountIdentifier !=null) {
			
			AccountDTO account = accountService.getAccountByAccountIdentifier(accountIdentifier);
			if(account!=null) {
				if(client!=null && client.getClientIdentifier().equals(account.getClientIdentifier())) {					
					List<IntervalBalanceDTO> list = accountService.getAccountBalanceStatsByInterval(accountIdentifier, interval);
					return new ResponseEntity<List<IntervalBalanceDTO>>(list, HttpStatus.OK);
				}else {
					throw new AccessDeniedException("You are not authorized to access this resource");
				}
			}
		}
		return null;
	}
	
	@GetMapping("/getCurrentBalance/{accountIdentifier}")
	public ResponseEntity<?> getCurrentAccountBalance(@PathVariable String accountIdentifier) throws AccessDeniedException, BusinessException{
		if(accountIdentifier !=null) {
			String balanceAmount = accountService.getAccountBalanceString(accountIdentifier);
			return new ResponseEntity<List<String>>(new ArrayList<String>(Arrays.asList(balanceAmount)), HttpStatus.OK);
		}
		return null;
	}
	
	@PostMapping("/addOrEditAccount")
	public ResponseEntity<?> addOrEditAccount(@RequestBody AccountDTO accountDTO) throws AccessDeniedException, BusinessException{
		ClientDTO client = SecurityUtils.getClientFromSession();
		if(client!=null) {
			AccountDTO account = accountService.addOrEditAccount(accountDTO, client);
			return new ResponseEntity<AccountDTO>(account, HttpStatus.OK);
			
		}else {
			throw new AccessDeniedException("You are not authorized for this action.");
		}
	}
}
