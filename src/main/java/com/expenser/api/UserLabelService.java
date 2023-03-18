package com.expenser.api;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.expenser.Entity.UserLabel;

@Service
public interface UserLabelService {
	
	Map<String, UserLabel> findLabelsByIdentifiers(List<String> identifiers);
}
