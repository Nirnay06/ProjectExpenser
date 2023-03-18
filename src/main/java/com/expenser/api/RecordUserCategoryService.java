package com.expenser.api;

import org.springframework.stereotype.Service;

import com.expenser.Entity.Client;
import com.expenser.Entity.RecordUserCategory;

@Service
public interface RecordUserCategoryService {

	RecordUserCategory findByIdentifier(String identifier);

	 void createDefaultCategories(Client newClient);
}
