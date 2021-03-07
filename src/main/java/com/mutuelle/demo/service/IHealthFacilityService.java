package com.mutuelle.demo.service;

import java.util.List;

import com.mutuelle.demo.model.HealthFacility;


public interface IHealthFacilityService
{
    String NAME = "healthFacilityService";

    List<HealthFacility> findAllHealthFacilities();

    HealthFacility findOne(long id);

    HealthFacility findByName(String name);

    HealthFacility create(HealthFacility hf);

}
