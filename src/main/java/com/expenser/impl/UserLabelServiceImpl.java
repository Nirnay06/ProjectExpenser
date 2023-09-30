package com.expenser.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.expenser.Entity.UserLabel;
import com.expenser.api.UserLabelService;
import com.expenser.model.UserLabelDTO;
import com.expenser.repository.UserLabelRepository;

@Service
public class UserLabelServiceImpl implements UserLabelService{

	@Autowired
	UserLabelRepository userLabelRepository;
	
	@Override
	public Map<String, UserLabel> findLabelsByIdentifiers(List<String> identifiers) {
		Map<String, UserLabel> map = new HashMap<String, UserLabel>();
		if(!CollectionUtils.isEmpty(identifiers)) {
			userLabelRepository.findUserLabelByIdentifiers(identifiers).stream().forEach(label -> {
				map.put(label.getIdentifier(), label);
			});
		}
		return map;
	}
	
	@Override
	@Cacheable(value = "labelCache")
	public List<UserLabelDTO> fetchAllActiveLabelsByClientIdentifier(String clientIdentifier) {
		return fetchLabelsByClientAndStatus(clientIdentifier, false);
	}

	public List<UserLabelDTO> fetchLabelsByClientAndStatus(String clientIdentifier, boolean isArchived) {
		return userLabelRepository.fetchLabelsByClientAndArchiveStatus(clientIdentifier, isArchived);
	}
	
	public List<UserLabelDTO> fetchDefaultLabelsByClientAndStatus(String clientIdentifier, boolean isArchived) {
		return userLabelRepository.fetchDefaultLabelsByClientAndArchiveStatus(clientIdentifier, isArchived);
	}

	@Override
	public List<String> fetchDefaultLabelsIdentifierByClientIdentifier(String clientIdentifier) {
		return userLabelRepository.fetchDefaultLabelsIdentifierByClientAndStatus(clientIdentifier, false);
	}

}
