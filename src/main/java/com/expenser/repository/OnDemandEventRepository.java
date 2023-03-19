package com.expenser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expenser.Entity.OnDemandEvent;

public interface OnDemandEventRepository extends JpaRepository<OnDemandEvent, Long>{

	@Query("select event from OnDemandEvent event where event.active=true")
	List<OnDemandEvent> findAllPendingEvents();

}
