package com.expenser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expenser.Entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	@Query(value = "select c from Client c where c.user.userIdentifier =:userIdentifier")
	Client findByUserIdentifier(String userIdentifier);

	Client findByClientIdentifier(String clientIdentifier);
}
