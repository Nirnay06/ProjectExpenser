package com.expenser.controller;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.expenser.service.OnInitService;

@Component
public class OnInitController {
	
	@Autowired
	OnInitService initService;
	
	Logger logger = LogManager.getLogger(OnInitController.class);
	
	@PostConstruct
	public void init() {
		logger.info("Init method triggering");
		initService.executeEvents();
		logger.info("Init method triggered");
	}
}
