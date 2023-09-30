package com.expenser.mapper;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.math.BigDecimal;

public class LongToBigDecimalConverter extends BidirectionalConverter<Long, BigDecimal> {

	@Override
	public BigDecimal convertTo(Long source, Type<BigDecimal> destinationType, MappingContext mappingContext) {
		   return source != null ? new BigDecimal(source) : null;
	}

	@Override
	public Long convertFrom(BigDecimal source, Type<Long> destinationType, MappingContext mappingContext) {
		return source != null ? source.longValue() : null;
	}
}
