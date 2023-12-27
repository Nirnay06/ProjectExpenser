package com.expenser.controller;

import java.net.http.HttpResponse;
import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenser.exception.BusinessException;
import com.expenser.model.APIResponseDTO;
import com.expenser.model.ClientDTO;
import com.expenser.model.RecordCategoryDTO;
import com.expenser.service.RecordUserCategoryService;
import com.expenser.util.SecurityUtils;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	RecordUserCategoryService recordUserCategoryService;
	
	@GetMapping("/findCategoriesForRecord")
	public ResponseEntity<?> fetchAllUserCategoryForRecord() throws AccessDeniedException, BusinessException{
		ClientDTO client = SecurityUtils.getClientFromSession();
		if(client!=null) {
			List<RecordCategoryDTO> list = recordUserCategoryService.findAllCategoryByClient(client.getClientIdentifier()); 
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		return new ResponseEntity<>(new APIResponseDTO("Something went wrong", false), HttpStatus.BAD_REQUEST);
	}
}
