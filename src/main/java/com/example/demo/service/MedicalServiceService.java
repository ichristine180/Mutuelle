package com.example.demo.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.MedicalService;
import com.example.demo.Repo.IMedicalServiceRepository;

/**
 * @Author: chabiman
 * @FileName: MedicalServiceService.java
 * @Date: Oct 15, 2020
 * @Package: rw.mutuelle.service
 * @ProjectName: mutuelle-payment-module
 *
 */
@Service(IMedicalServiceService.NAME)
public class MedicalServiceService implements IMedicalServiceService {

	/** The medical service repository. */
	@Autowired
	private IMedicalServiceRepository medicalServiceRepository;

	/*
	 *
	 * @see rw.mutuelle.service.IMedicalServiceService#createMedicalService(rw.mutuelle.model.MedicalService)
	 */
	@Override
	public MedicalService createMedicalService(MedicalService medicalService) {
		try {
			return medicalServiceRepository.save(medicalService);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/*
	 *
	 * @see rw.mutuelle.service.IMedicalServiceService#findAll()
	 */
	@Override
	public List<MedicalService> findAll() {
		try {
			return medicalServiceRepository.findAll();
		} catch (Exception ex) {
			throw ex;
		}
	}
}
