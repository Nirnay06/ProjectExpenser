package com.expenser.mapper;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.expenser.Entity.RecordUserCategory;
import com.expenser.Entity.UserCurrency;
import com.expenser.Entity.UserCurrency;
import com.expenser.model.CurrencyDTO;
import com.expenser.model.RecordCategoryDTO;
import com.expenser.model.CurrencyDTO;
import com.expenser.util.DateUtil;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class CategoryMapper  extends ConfigurableMapper{


	private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
	
	public CategoryMapper() {
		configure();
	}
	public void configure() {
		mapperFactory.classMap(RecordUserCategory.class, RecordCategoryDTO.class).exclude("parent")
				.exclude("children").exclude("client").byDefault().mapNulls(false).register();
	}

    public RecordCategoryDTO toDto(RecordUserCategory category) {
        return mapperFactory.getMapperFacade(RecordUserCategory.class, RecordCategoryDTO.class).map(category);
    }

    public RecordUserCategory toEntity(RecordCategoryDTO categoryDTO) {
        return mapperFactory.getMapperFacade(RecordCategoryDTO.class, RecordUserCategory.class).map(categoryDTO);
    }
}
