package com.expenser.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.expenser.Entity.RecordUserCategory;

@Repository
public interface RecordCategoryRepository extends JpaRepository<RecordUserCategory, Long>{
	
	public RecordUserCategory findByIdentifier(String RecordCategoryIdentifier);
	
	@Query(value = "select rc from UserRecordCategory rc where rc.record.identifier =:recordIdentifier")
	public RecordUserCategory findByRecordIdentifier(@Param("recordIdentifier") String recordIdentifier);
	
	@Query(value ="select rc from UserRecordCategory rc where rc.user.userIdentifier =:userIdentifier")
	public List<RecordUserCategory> findByUserIdentifier(@Param("userIdentifier") String userIdentifier);
	
}
