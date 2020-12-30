package com.example.demo.service;

import java.time.LocalDate;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.demo.Model.MedicalAct;
import com.example.demo.Model.Patient;




/**
 * @Author: chabiman
 * @FileName: IMedicalActService.java
 * @Date: Oct 15, 2020
 * @Package: rw.mutuelle.service
 * @ProjectName: mutuelle-payment-module
 *
 */
public interface IMedicalActService {

	String NAME = "medicalActService";

	/**
	 * Creates the medical act.
	 *
	 * @param medicalAct the medical act
	 * @return the medical act
	 */
	public MedicalAct createMedicalAct(@NotNull MedicalAct medicalAct);

	/**
	 * Find all act by patient.
	 *
	 * @param patient the patient
	 * @return the list
	 */
	public List<MedicalAct> findAllActByPatient(@NotNull Patient patient);

	/**
	 * Find one date act by patient.
	 *
	 * @param patient the patient
	 * @param date    the date
	 * @return the list
	 */
	public List<MedicalAct> findOneDateActByPatient(@NotNull Patient patient, @NotNull LocalDate date);

}
