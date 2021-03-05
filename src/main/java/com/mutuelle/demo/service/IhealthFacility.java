package com.mutuelle.demo.service;

import java.util.List;

import com.mutuelle.demo.model.HealthFacility;

public interface IhealthFacility {
	String NAME = "healthFacilityService";

	List<HealthFacility> findAllService();

	HealthFacility findOne(long id);

	HealthFacility create(HealthFacility hf);

}
