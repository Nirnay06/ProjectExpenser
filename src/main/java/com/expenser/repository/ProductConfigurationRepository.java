package com.expenser.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expenser.Entity.ProductConfiguration;

public interface ProductConfigurationRepository extends JpaRepository<ProductConfiguration, Long>{

	@Query("select pc.value from ProductConfiguration pc where pc.key=:key")
	String findValueByKey(@Param("key") String key);
	
}
