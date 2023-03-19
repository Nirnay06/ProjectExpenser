package com.expenser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expenser.Entity.UserLabel;
import com.expenser.model.UserLabelDTO;

@Repository
public interface UserLabelRepository  extends JpaRepository<UserLabel, Long>{
	
	@Query(value = "select l from UserLabel l where l.identifier in (:identifiers)")
	List<UserLabel> findUserLabelByIdentifiers(List<String> identifiers);

	@Query(value = "select new com.expenser.model.UserLabelDTO(l.identifier, l.client.clientIdentifier, "
			+ " l.archive, l.title, l.color, l.defaultAssign) from UserLabel l where l.client.clientIdentifier=:clientIdentifier and l.archive=:isArchived")
	List<UserLabelDTO> fetchLabelsByClientAndArchiveStatus(String clientIdentifier, boolean isArchived);
}