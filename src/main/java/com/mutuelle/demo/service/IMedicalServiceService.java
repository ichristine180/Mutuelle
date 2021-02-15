package com.mutuelle.demo.service;

import java.util.List;


import javax.validation.constraints.NotNull;

import com.mutuelle.demo.Model.MedicalService;
import com.mutuelle.demo.utils.EMedicalServiceType;


public interface IMedicalServiceService {

	/** The name. */
	String NAME = "medicalServiceService";

	/**
	 * Creates the medical service.
	 *
	 * @param medicalService the medical service
	 * @return the medical service
	 */
	public MedicalService createMedicalService(@NotNull MedicalService medicalService);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<MedicalService> findAll();
	public List<MedicalService> findExams();
	public List<MedicalService> findMedicaments(EMedicalServiceType type);
	public MedicalService findById(Long id);

}
