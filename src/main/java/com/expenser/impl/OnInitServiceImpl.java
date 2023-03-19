package com.expenser.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenser.Entity.OnDemandEvent;
import com.expenser.api.OnInitService;
import com.expenser.repository.OnDemandEventRepository;

@Service
public class OnInitServiceImpl implements OnInitService{

	@Autowired
	OnDemandEventRepository onDemandEventRepository;
	
    Logger logger = LoggerFactory.getLogger(OnInitServiceImpl.class);
    
	@Override
	public void executeEvents() {
		List<OnDemandEvent> events = onDemandEventRepository.findAllPendingEvents();
		for(OnDemandEvent event : events) {
			try {
				//No implementation is done for now
			}catch(Exception e) {
				event.setErrorMsg(e.getStackTrace().toString());
				logger.error(e.getStackTrace().toString());
			}finally {
				event.setActive(false);
			}
		}
	}

}
