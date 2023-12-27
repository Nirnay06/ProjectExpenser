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

	@Query("select new com.expenser.model.CurrencyDTO(uc.identifier, uc.client.clientIdentifier, uc.currency.currencyTitle, uc.currency.currencySymbol, uc.currencyRate, "
			+ "			uc.rateOverriden, uc.baseCurrency, uc.currency.identifier, uc.currency.currencyAbbreviation, uc.displayOrder) from UserCurrency uc "
			+ "where uc.client.clientIdentifier= :clientIdentifier ")
	public List<CurrencyDTO> findByClientIdentifier(String clientIdentifier);

	@Query("select uc from UserCurrency uc where uc.currency.identifier=:masterCurrencyIdentifier "
			+ " and uc.client.clientIdentifier = :clientIdentifier")
	public UserCurrency findByMasterCurrencyIdentifier(String masterCurrencyIdentifier, String clientIdentifier);

	@Query("select max(uc.displayOrder) from UserCurrency uc where uc.client.clientIdentifier = :clientIdentifier")
	public int findMaxDisplayOrderByClient(String clientIdentifier);
}
