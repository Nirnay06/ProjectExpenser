package com.expenser.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Cache;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.expenser.Entity.Client;
import com.expenser.Entity.RecordUserCategory;
import com.expenser.model.RecordCategoryDTO;
import com.expenser.repository.RecordUserCategoryReporsitory;
import com.expenser.service.RecordUserCategoryService;
import com.expenser.util.SecurityUtils;

@Service
public class RecordUserCategoryServiceImpl implements RecordUserCategoryService{

	@Autowired
	private RecordUserCategoryReporsitory recordUserCategoryRepository;
	
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    ModelMapper mapper;
	
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
//			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("createDefaultCategoryForClient");
//			query.registerStoredProcedureParameter("clientIdentifier", String.class, ParameterMode.IN);
//			query.registerStoredProcedureParameter("createdBy", String.class, ParameterMode.IN);
//			query.setParameter("clientIdentifier", newClient.getClientIdentifier());
//			query.setParameter("createdBy", newClient.getUser().getUserIdentifier());
//			query.execute();
			recordUserCategoryRepository.createDefaultCategories(newClient.getClientIdentifier(), newClient.getUser().getUserIdentifier());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	public List<RecordCategoryDTO> findCategoryListByClientAndStatus(String clientIdentifier, boolean hidden){
		if(clientIdentifier !=null) {
			List<RecordUserCategory> records = recordUserCategoryRepository.findCategoryTreeByClientAndHiddenStatus(clientIdentifier, hidden);
			if(!CollectionUtils.isEmpty(records)) {
				List<RecordCategoryDTO> list = RecordCategoryDTO.mapCategoryListToDTO(records,null);			
				return list;
			}
			return null;
		}
		return null;
	}
	
	@Override
	@Cacheable("categoryCache")
	public List<RecordCategoryDTO> findAllCategoryByClient(String clientIdentifier) {
		return findCategoryListByClientAndStatus(clientIdentifier, false);
	}

}
