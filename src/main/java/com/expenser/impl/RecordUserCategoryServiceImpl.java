package com.expenser.impl;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.expenser.Entity.Client;
import com.expenser.Entity.RecordUserCategory;
import com.expenser.api.RecordUserCategoryService;
import com.expenser.repository.RecordUserCategoryReporsitory;
import com.expenser.util.SecurityUtils;

@Service
public class RecordUserCategoryServiceImpl implements RecordUserCategoryService{

	@Autowired
	private RecordUserCategoryReporsitory recordUserCategoryRepository;
	
    @PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public RecordUserCategory findByIdentifier(String identifier) {
		if(identifier!=null) {
			return recordUserCategoryRepository.findByIdentifier(identifier);
		}
		return null;
	}


	@Override
	@Async
	public void createDefaultCategories(Client newClient) {
		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createDefaultCategoryForClient");
			query.registerStoredProcedureParameter("clientIdentifier", String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter("createdBy", String.class, ParameterMode.IN);
			query.setParameter("clientIdentifier", newClient.getClientIdentifier());
			query.setParameter("createdBy", SecurityUtils.getLoggedUserFromSession().getUserIdentifier());
			query.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
