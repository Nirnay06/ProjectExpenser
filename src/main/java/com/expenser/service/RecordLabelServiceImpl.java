package com.expenser.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.expenser.Entity.RecordLabel;
import com.expenser.Entity.UserLabel;
import com.expenser.Entity.UserRecord;
import com.expenser.model.RecordDTO;

@Service
public class RecordLabelServiceImpl implements RecordLabelService{
	
	@Autowired
	UserLabelService userLabelService;
	
	@Override 
	public void setRecordLabelFromRecordDTO(RecordDTO recordDTO, UserRecord record){
		if(!CollectionUtils.isEmpty(recordDTO.getLabels())) {
			Map<String, UserLabel> userLabelMap=userLabelService.findLabelsByIdentifiers(recordDTO.getLabels().stream().map(l -> l.getUserLabelIdentifier()).collect(Collectors.toList()));
			List<RecordLabel> labels = new ArrayList<RecordLabel>();
			recordDTO.getLabels().stream().forEach(label -> {
				RecordLabel rLabel = new RecordLabel(record,userLabelMap.get(label.getUserLabelIdentifier()));
				labels.add(rLabel);
			});
			record.setLabels(labels);
		}
	}
}
