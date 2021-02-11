package com.mutuelle.demo.service;

import java.time.LocalDate;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.Model.MedicalAct;
import com.mutuelle.demo.Model.Patient;
import com.mutuelle.demo.Repo.IMedicalActRepository;
@Service(IMedicalActService.NAME)
public class MedicalActService implements IMedicalActService {

	@Autowired
	private IMedicalActRepository medicalActRepository;

	/*
	 *
	 * @see rw.mutuelle.service.IMedicalActService#createMedicalAct(rw.mutuelle.model.MedicalAct)
	 */
	@Override
	public MedicalAct createMedicalAct(MedicalAct medicalAct) {
		try {
			return medicalActRepository.save(medicalAct);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/*
	 *
	 * @see rw.mutuelle.service.IMedicalActService#findAllActByPatient(rw.mutuelle.model.Patient)
	 */
	@Override
	public List<MedicalAct> findAllActByPatient(Patient patient) {
		try {
			return medicalActRepository.findByPatient(patient);
		} catch (Exception ex) {
			throw ex;
		}
	}

	/*
	 *
	 * @see rw.mutuelle.service.IMedicalActService#findOneDateActByPatient(rw.mutuelle.model.Patient,
	 * java.time.LocalDate)
	 */
	@Override
	public List<MedicalAct> findOneDateActByPatient(Patient patient, LocalDate date) {
		try {
			return medicalActRepository.findByPatientAndDate(patient, date);
		} catch (Exception ex) {
			throw ex;
		}
	}

}
