package com.expenser.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expenser.Entity.Client;
import com.expenser.Entity.User;
import com.expenser.api.ClientService;
import com.expenser.api.RecordUserCategoryService;
import com.expenser.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RecordUserCategoryService recordUserCategoryService;
	
	@Override
	public void addClient() {
		
	}

	@Override
	public Client findByClientIdentifier(String clientIdentifier) {
		if(clientIdentifier != null) {
			Client client = clientRepository.findByClientIdentifier(clientIdentifier);
			return client;
		}
		return null;
	}

	@Override
	public Client findByUserIdentifier(String userIdentifier) {
		
		if(userIdentifier != null) {
			Client client = clientRepository.findByUserIdentifier(userIdentifier);
			return client;
		}
		return null;
	}

	@Override
	@Transactional
	public void createNewClient(User user) {
		Client c = new Client(user);
		Client newClient = clientRepository.save(c);
		if(newClient!=null) {
			recordUserCategoryService.createDefaultCategories(newClient);
		}
		
	}
}
