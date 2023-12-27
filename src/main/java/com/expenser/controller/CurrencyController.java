package com.expenser.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenser.Entity.ExpenserCurrency;
import com.expenser.exception.BusinessException;
import com.expenser.model.APIResponseDTO;
import com.expenser.model.ClientDTO;
import com.expenser.model.CurrencyDTO;
import com.expenser.service.MasterCurrencyService;
import com.expenser.service.UserCurrencyService;
import com.expenser.util.SecurityUtils;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

	
	@Autowired
	UserCurrencyService userCurrencyService;
	
	@Autowired
	private MasterCurrencyService masterCurrencyService;
	
	
	@GetMapping("/listMasterCurrency")
	public ResponseEntity<?> listAllMasterCurrency() throws AccessDeniedException, BusinessException{
		//this is a public API and no need to check for Client permissions
		List<ExpenserCurrency> currencyList =  masterCurrencyService.getAllMasterCurrency();
		return new ResponseEntity<List<ExpenserCurrency>>(currencyList, HttpStatus.OK);
	}
	
	@GetMapping("/currencyListForRecord")
	public ResponseEntity<?> getCurrenciesForRecordByClient() throws AccessDeniedException, BusinessException{
		ClientDTO client = SecurityUtils.getClientFromSession();
		if(client!=null) {
			List<CurrencyDTO> currencyList = userCurrencyService.fetchActiveCurrenciesByClient(client.getClientIdentifier());
			return new ResponseEntity<List<CurrencyDTO>>(currencyList, HttpStatus.OK);
		}
		throw new AccessDeniedException(null);
	}
	
	@GetMapping("/listMasterCurrencyNotSelected")
	public ResponseEntity<?> listAllMasterCurrencyNotSelectedByClient() throws AccessDeniedException, BusinessException{
		//this is a public API and no need to check for Client permissions
		ClientDTO client = SecurityUtils.getClientFromSession();
		if(client!=null) {			
			List<ExpenserCurrency> currencyList =  masterCurrencyService.getAllMasterCurrencyNotAssignedToUser(client);
			return new ResponseEntity<List<ExpenserCurrency>>(currencyList, HttpStatus.OK);
		}
		throw new AccessDeniedException(null);
	}
	
	@PostMapping("/assignCurrencyToUser")
	public ResponseEntity<?> assignCurrencyToUser(@RequestBody CurrencyDTO userCurrencyDTO,  HttpServletRequest request) throws AccessDeniedException, BusinessException{
		ClientDTO client = SecurityUtils.getClientFromSession();
			if (userCurrencyDTO != null && client != null) {
				userCurrencyService.addUserCurrency(userCurrencyDTO, client);
				return new ResponseEntity<>(new APIResponseDTO("Currency added successfully.", true), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new APIResponseDTO("Something went wrong", false),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@PostMapping("/editUserCurrency")
	public ResponseEntity<?> editUserCurrency(@RequestBody CurrencyDTO userCurrencyDTO,  HttpServletRequest request) throws AccessDeniedException, BusinessException{
		ClientDTO client = SecurityUtils.getClientFromSession();
			if (userCurrencyDTO != null && client != null) {
				userCurrencyService.updateUserCurrency(userCurrencyDTO, client);
				return new ResponseEntity<>(new APIResponseDTO("Currency updated successfully.", true), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new APIResponseDTO("Something went wrong", false),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@PutMapping("/removeUserCurrency/{currencyIdentifier}")
	public ResponseEntity<?> deleteUserCurrency(@PathVariable String currencyIdentifier, HttpServletRequest request) throws AccessDeniedException, BusinessException{
		ClientDTO client = SecurityUtils.getClientFromSession();
			if (currencyIdentifier != null && client != null) {
				userCurrencyService.deleteUserCurrency(currencyIdentifier, client);
				return new ResponseEntity<>(new APIResponseDTO("Currency deleted successfully.", true), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new APIResponseDTO("Something went wrong", false),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
}
