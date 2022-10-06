package com.expenser.controller;

import java.nio.file.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.expenser.Entity.User;
import com.expenser.api.UserService;
import com.expenser.model.APIResponseDTO;
import com.expenser.model.RecordDTO;
import com.expenser.model.UserDTO;
import com.expenser.util.SecurityUtils;

@RestController
@RequestMapping("/record")
public class RecordController {
	
	@Autowired
	UserService userService;

	@Autowired
	ModelMapper mapper;

	@PostMapping("/add")
	public ResponseEntity<?> addAccountRecord(@RequestBody RecordDTO recordDTO, HttpServletRequest request) throws AccessDeniedException {
		UserDTO user = SecurityUtils.getUserFromSession();
		if (recordDTO != null) {
			if (recordDTO.getUser() != null && recordDTO.getUser().getUserIdentifier() != null) {
				if(user.getUserIdentifier().equals(recordDTO.getUser().getUserIdentifier())) {
					
					return new ResponseEntity<>(new APIResponseDTO("Record added successfully.", true), HttpStatus.OK);
				}
			}
			throw new AccessDeniedException(null);
		} else {
			return new ResponseEntity<>(new APIResponseDTO("Something went wrong", false),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
