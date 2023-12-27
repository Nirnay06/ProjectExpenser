package com.expenser.service;

import org.springframework.stereotype.Service;

import com.expenser.Entity.Client;
import com.expenser.Entity.User;
import com.expenser.model.ClientDTO;

@Service
public interface ClientService {

	void addClient();
	
	Client findByClientIdentifier(String clientIdentifier);
	
	Client findByUserIdentifier(String userIdentifier);

	void createNewClient(User user);
}
