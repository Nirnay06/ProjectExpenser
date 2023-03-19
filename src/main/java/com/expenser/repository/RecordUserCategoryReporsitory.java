package com.expenser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expenser.Entity.RecordUserCategory;

public interface RecordUserCategoryReporsitory extends JpaRepository<RecordUserCategory, Long>{

	@Query(value = "select c from RecordUserCategory c where c.identifier= :identifier")
	RecordUserCategory findByIdentifier(String identifier);

	@Query(value="select c from RecordUserCategory c where c.client.clientIdentifier = :clientIdentifier and c.hidden=:hidden")
	List<RecordUserCategory> findCategoryByClientAndHiddenStatus(String clientIdentifier, boolean hidden);

	@Query(value="select c from RecordUserCategory c where c.client.clientIdentifier = :clientIdentifier and c.hidden=:hidden and c.parent is null")
	List<RecordUserCategory> findCategoryTreeByClientAndHiddenStatus(String clientIdentifier, boolean hidden);
}
