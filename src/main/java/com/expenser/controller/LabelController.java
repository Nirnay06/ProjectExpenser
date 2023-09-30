package com.expenser.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expenser.Entity.UserLabel;
import com.expenser.api.UserLabelService;
import com.expenser.exception.BusinessException;
import com.expenser.model.APIResponseDTO;
import com.expenser.model.ClientDTO;
import com.expenser.model.UserLabelDTO;
import com.expenser.util.SecurityUtils;

@RestController
@RequestMapping("/label")
public class LabelController {

	@Autowired
	UserLabelService userLabelService;
	
	
	@GetMapping("/getLabelsForRecord")
	public ResponseEntity<?> fetchAllLabelsForRecord() throws AccessDeniedException, BusinessException{
		ClientDTO client = SecurityUtils.getClientFromSession();
		if(client!=null) {
			List<UserLabelDTO> labels = userLabelService.fetchAllActiveLabelsByClientIdentifier(client.getClientIdentifier());
			return new ResponseEntity<>(labels, HttpStatus.OK);
		}
		return new ResponseEntity<>(new APIResponseDTO("Something went wrong", false), HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getDefaultLabels")
	public ResponseEntity<?> fetchDefaultLabelsForUser() throws AccessDeniedException, BusinessException{
		ClientDTO client = SecurityUtils.getClientFromSession();
		if(client!=null) {
			List<String> labels = userLabelService.fetchDefaultLabelsIdentifierByClientIdentifier(client.getClientIdentifier());
			return new ResponseEntity<>(labels, HttpStatus.OK);
		}
		return new ResponseEntity<>(new APIResponseDTO("Something went wrong", false), HttpStatus.BAD_REQUEST);
	}
}
