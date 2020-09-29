package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Patient;
import com.example.demo.Repo.PatientRepository;

@Service(IPatientService.NAME)
public class PatientService implements IPatientService {

	@Autowired
	private PatientRepository pRepository;

	@Override
	public Patient findPatientByidnb(String idnb) {
		try {
			Patient patientPatient = pRepository.findByidnb(idnb);
			if (patientPatient != null) {
				return patientPatient;
			} else {
				System.out.println("there is no patient with that id number");
				return null;
			}

		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Patient> findAll() {
		try {
			return pRepository.findAll();
		} catch (Exception exc) {
			throw exc;
		}
	}

}
