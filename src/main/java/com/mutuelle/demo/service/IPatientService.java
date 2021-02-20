package com.mutuelle.demo.service;

import java.util.List;


import javax.validation.constraints.NotNull;

import com.mutuelle.demo.Model.Patient;

public interface IPatientService {

	String NAME = "patientService";

	/**
	 * Creates the patient.
	 *
	 * @param patient the patient
	 * @return the patient
	 */
	public Patient createPatient(@NotNull Patient patient);

	/**
	 * Find all patient.
	 *
	 * @return the list
	 */
	public List<Patient> findAllPatient();

	/**
	 * Find patient by id.
	 *
	 * @param id the id
	 * @return the patient
	 */
	public Patient findPatientById(@NotNull Long id);

	public Patient findPatientByIdnbr(String idnbr);

}
