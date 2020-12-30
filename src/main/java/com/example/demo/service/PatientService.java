package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Patient;
import com.example.demo.Repo.IPatientRepository;



/**
 * @Author: chabiman
 * @FileName: PatientService.java
 * @Date: Oct 15, 2020
 * @Package: rw.mutuelle.service
 * @ProjectName: mutuelle-payment-module
 *
 */
@Service(IPatientService.NAME)
@Transactional
public class PatientService implements IPatientService {

	/** The patient repository. */
	@Autowired
	private IPatientRepository patientRepository;

	/*
	 *
	 * @see rw.mutuelle.service.IPatientService#createPatient(rw.mutuelle.model.Patient)
	 */
	@Override
	public Patient createPatient(Patient patient) {
		try {
			return patientRepository.save(patient);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/*
	 *
	 * @see rw.mutuelle.service.IPatientService#findAllPatient()
	 */
	@Override
	public List<Patient> findAllPatient() {
		try {
			return patientRepository.findAll();
		} catch (Exception ex) {
			throw ex;
		}
	}

	/*
	 *
	 * @see rw.mutuelle.service.IPatientService#findPatientById(java.lang.Long)
	 */
	@Override
	public Patient findPatientById(Long id) {
		try {
			
		} catch (Exception ex) {
			throw ex;
		}
		return null;
	}

	public List<Patient> listAll() {
		return patientRepository.findAll();
	}

	public void save(Patient pat) {
		patientRepository.save(pat);
	}

	

	
	}

