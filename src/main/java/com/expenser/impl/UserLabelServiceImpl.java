package com.expenser.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.expenser.Entity.UserLabel;
import com.expenser.api.UserLabelService;
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

}
