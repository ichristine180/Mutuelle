package com.example.demo.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.demo.Model.Patient;



/**
 * @Author: chabiman
 * @FileName: IPatientService.java
 * @Date: Oct 15, 2020
 * @Package: rw.mutuelle.service
 * @ProjectName: mutuelle-payment-module
 *
 */
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

}
