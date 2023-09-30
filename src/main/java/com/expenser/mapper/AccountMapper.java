package com.expenser.mapper;

import org.springframework.stereotype.Component;

import com.expenser.Entity.UserAccount;
import com.expenser.model.AccountDTO;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class AccountMapper extends ConfigurableMapper {

	private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

	public AccountMapper() {
		configure();
	}

	public void configure() {
		mapperFactory.getConverterFactory().registerConverter(new LongToBigDecimalConverter());
		mapperFactory.classMap(UserAccount.class, AccountDTO.class).byDefault().mapNulls(false).register();
	}

	public AccountDTO toDto(UserAccount account) {
		return mapperFactory.getMapperFacade(UserAccount.class, AccountDTO.class).map(account);
	}

	public UserAccount toEntity(AccountDTO accountDTO) {
		return mapperFactory.getMapperFacade(AccountDTO.class, UserAccount.class).map(accountDTO);
	}
}
