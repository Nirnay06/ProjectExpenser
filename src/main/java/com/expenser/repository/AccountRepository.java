package com.expenser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expenser.Entity.UserAccount;

@Repository
public interface AccountRepository extends JpaRepository<UserAccount,Long>{

	public UserAccount findByIdentifier(String accountIdentifier);
}
