package com.expenser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.expenser.Entity.Authority;

public interface AutorityRepository  extends JpaRepository<Authority, Long>{

}
