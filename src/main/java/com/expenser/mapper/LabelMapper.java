package com.expenser.mapper;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.expenser.Entity.RecordLabel;
import com.expenser.Entity.UserCurrency;
import com.expenser.Entity.UserCurrency;
import com.expenser.model.CurrencyDTO;
import com.expenser.model.RecordLabelDTO;
import com.expenser.model.CurrencyDTO;
import com.expenser.util.DateUtil;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class LabelMapper extends ConfigurableMapper {

	private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

	public LabelMapper() {
		configure();
	}

	public void configure() {
		mapperFactory.getConverterFactory().registerConverter(new LongToBigDecimalConverter());
		mapperFactory.classMap(RecordLabel.class, RecordLabelDTO.class)
					.field("userLabel.identifier", "userLabelIdentifier").byDefault().mapNulls(false).register();
	}

	public RecordLabelDTO toDto(RecordLabel currency) {
		return mapperFactory.getMapperFacade(RecordLabel.class, RecordLabelDTO.class).map(currency);
	}

	public RecordLabel toEntity(RecordLabelDTO currencyDTO) {
		return mapperFactory.getMapperFacade(RecordLabelDTO.class, RecordLabel.class).map(currencyDTO);
	}
}
