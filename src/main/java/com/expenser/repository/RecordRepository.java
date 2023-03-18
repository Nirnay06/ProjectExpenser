package com.expenser.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.expenser.Entity.UserRecord;

@Repository
public interface RecordRepository extends JpaRepository<UserRecord, Long>{
	
	public UserRecord findByIdentifier(String identifier);
	
	@Query(value = "select ur from UserRecord ur where ur.client.clientIdentifier =:clientIdentifier")
	public List<UserRecord> findByClient(@Param("userIdentifier") String clientIdentifier);
	
	@Query(value = "select ur from UserRecord ur where ur.account.identifier =:accountIdentifier")
	public List<UserRecord> findByAccount(@Param("accountIdentifier") String accountIdentifier);
	
}
