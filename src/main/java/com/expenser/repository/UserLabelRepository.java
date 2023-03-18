package com.expenser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expenser.Entity.UserLabel;

@Repository
public interface UserLabelRepository  extends JpaRepository<UserLabel, Long>{

	@Query(value = "select l from UserLabel l where l.identifier in (:identifiers)")
	List<UserLabel> findUserLabelByIdentifiers(List<String> identifiers);
}
