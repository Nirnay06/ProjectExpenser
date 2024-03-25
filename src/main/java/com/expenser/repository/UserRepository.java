package com.expenser.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.expenser.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUsername(String username);
	User findByUserIdentifier(String userIdentifier);

}
