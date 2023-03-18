package com.expenser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expenser.Entity.RecordUserCategory;

public interface RecordUserCategoryReporsitory extends JpaRepository<RecordUserCategory, Long>{

	@Query(value = "select c from RecordUserCategory c where c.identifier= :identifier")
	RecordUserCategory findByIdentifier(String identifier);
}
