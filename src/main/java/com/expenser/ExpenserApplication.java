package com.expenser;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.expenser.mapper.LongToBigDecimalConverter;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@ComponentScans({ @ComponentScan("com.expenser.controller"), @ComponentScan("com.expenser.config"),
		@ComponentScan("com.expenser.security"), @ComponentScan("com.expenser.eventListener"), @ComponentScan("com.expenser.util"),
		@ComponentScan("com.expenser.mapper")})
@EntityScan("com.expenser.Entity")
@EnableJpaRepositories("com.expenser.repository")
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class ExpenserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenserApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper =  new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}
	
//	@Bean
//    public MapperFactory mapperFactory() {
//		MapperFactory mapperFactory =  new DefaultMapperFactory.Builder().build();
//		mapperFactory.getConverterFactory().registerConverter(new LongToBigDecimalConverter());
//        return mapperFactory;
//    }
}
