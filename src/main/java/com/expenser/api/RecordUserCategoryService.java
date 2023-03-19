package com.expenser.api;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expenser.Entity.Client;
import com.expenser.Entity.RecordUserCategory;
import com.expenser.model.RecordCategoryDTO;

@Service
public interface RecordUserCategoryService {

	RecordUserCategory findByIdentifier(String identifier);

	 void createDefaultCategories(Client newClient);

	List<RecordCategoryDTO> findAllCategoryByClient(String clientIdentifier);
}
