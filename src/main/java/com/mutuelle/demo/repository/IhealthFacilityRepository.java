package com.mutuelle.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.model.HealthFacility;

@Repository
public interface IhealthFacilityRepository  extends JpaRepository<HealthFacility, Long>{

	HealthFacility findByFacilityName(String name);

}
