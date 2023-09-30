package com.expenser.config;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.expenser.mapper.LongToBigDecimalConverter;

@Configuration
public class OrikaConfig {

	@Bean
    public MapperFactory mapperFactory() {
		MapperFactory mapperFactory =  new DefaultMapperFactory.Builder().build();
		mapperFactory.getConverterFactory().registerConverter(new LongToBigDecimalConverter());
        return mapperFactory;
    }

}
