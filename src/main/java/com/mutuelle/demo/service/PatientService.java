package com.mutuelle.demo.service;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.Model.Patient;
import com.mutuelle.demo.Repo.IPatientRepository;

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

	@Override
	public Patient findPatientByIdnbr(String idnbr) {
		// TODO Auto-generated method stub
		try {
			return patientRepository.findByIdnb(idnbr);
		}
catch (Exception e) {
	// TODO: handle exception
	throw e;
}		
	}

	

	
	}

