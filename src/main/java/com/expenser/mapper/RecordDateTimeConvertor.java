package com.expenser.mapper;
import java.util.Date;

import com.expenser.model.RecordDTO;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class RecordDateTimeConvertor  extends BidirectionalConverter<Date, RecordDTO>{

	@Override
	public RecordDTO convertTo(Date source, Type<RecordDTO> destinationType, MappingContext mappingContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date convertFrom(RecordDTO source, Type<Date> destinationType, MappingContext mappingContext) {
		// TODO Auto-generated method stub
		return null;
	}
}
