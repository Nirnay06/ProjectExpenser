package com.expenser.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expenser.Entity.UserCurrency;
import com.expenser.model.CurrencyDTO;

@Repository
public interface UserCurrencyRepository extends JpaRepository<UserCurrency, Long>{

	public UserCurrency findByIdentifier(String userCurrencyIdentifier);

	@Query("select new com.expenser.model.CurrencyDTO(uc.identifier, uc.client.clientIdentifier, uc.title, uc.icon, uc.currencyRate, "
			+ "			uc.rateOverriden, uc.baseCurrency, uc.currency.identifier) from UserCurrency uc "
			+ "where uc.client.clientIdentifier= :clientIdentifier ")
	public List<CurrencyDTO> findByClientIdentifier(String clientIdentifier);
}
