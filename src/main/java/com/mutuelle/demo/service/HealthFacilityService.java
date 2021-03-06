package com.mutuelle.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.repository.IhealthFacilityRepository;

import com.mutuelle.demo.model.HealthFacility;

@Service(IhealthFacility.NAME)
@Transactional
public class HealthFacilityService implements IhealthFacility {
	@Autowired
	private IhealthFacilityRepository hRepo;

	@Override
	public List<HealthFacility> findAllService() {
		// TODO Auto-generated method stub
		try {
			return hRepo.findAll();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public HealthFacility findOne(long id) {
		// TODO Auto-generated method stub
		try {
			return hRepo.findOne(id);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public HealthFacility create(HealthFacility hf) {
		// TODO Auto-generated method stub
		try {
			return hRepo.save(hf);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public HealthFacility findByName(String name) {
		try {
			return hRepo.findByFacilityName(name);
		} catch (Exception e) {
			throw e;
		}
	}

}
