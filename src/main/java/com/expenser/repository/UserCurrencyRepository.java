package com.expenser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expenser.Entity.UserCurrency;

@Repository
public interface UserCurrencyRepository extends JpaRepository<UserCurrency, Long>{

	public UserCurrency findByIdentifier(String userCurrencyIdentifier);
}
