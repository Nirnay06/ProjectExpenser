package com.expenser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expenser.Entity.ExpenserCurrency;

@Repository
public interface MasterCurrencyRepository extends JpaRepository<ExpenserCurrency, Long> {

	public List<ExpenserCurrency> findAll();

	public ExpenserCurrency findByIdentifier(String masterCurrencyIdentifier);
}
