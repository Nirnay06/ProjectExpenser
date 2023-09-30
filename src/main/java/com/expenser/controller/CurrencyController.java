package com.expenser.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenser.api.UserCurrencyService;
import com.expenser.exception.BusinessException;
import com.expenser.model.ClientDTO;
import com.expenser.model.CurrencyDTO;
import com.expenser.util.SecurityUtils;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

	
	@Autowired
	UserCurrencyService userCurrencyService;
	
	@GetMapping("/currencyListForRecord")
	public ResponseEntity<?> getCurrenciesForRecordByClient() throws AccessDeniedException, BusinessException{
		ClientDTO client = SecurityUtils.getClientFromSession();
		if(client!=null) {
			List<CurrencyDTO> currencyList = userCurrencyService.fetchActiveCurrenciesByClient(client.getClientIdentifier());
			return new ResponseEntity<List<CurrencyDTO>>(currencyList, HttpStatus.OK);
		}
		throw new AccessDeniedException(null);
	}
}
