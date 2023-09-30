package com.expenser.mapper;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.expenser.Entity.UserCurrency;
import com.expenser.Entity.UserCurrency;
import com.expenser.model.CurrencyDTO;
import com.expenser.model.CurrencyDTO;
import com.expenser.util.DateUtil;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class CurrencyMapper extends ConfigurableMapper {

	private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

	public CurrencyMapper() {
		configure();
	}

	public void configure() {
		mapperFactory.getConverterFactory().registerConverter(new LongToBigDecimalConverter());
		mapperFactory.classMap(UserCurrency.class, CurrencyDTO.class).byDefault().mapNulls(false).register();
	}

	public CurrencyDTO toDto(UserCurrency currency) {
		return mapperFactory.getMapperFacade(UserCurrency.class, CurrencyDTO.class).map(currency);
	}

	public UserCurrency toEntity(CurrencyDTO currencyDTO) {
		return mapperFactory.getMapperFacade(CurrencyDTO.class, UserCurrency.class).map(currencyDTO);
	}
}
