package com.example.demo.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.example.demo.Model.MedicalService;


/**
 * @Author: chabiman
 * @FileName: IMedicalServiceService.java
 * @Date: Oct 15, 2020
 * @Package: rw.mutuelle.service
 * @ProjectName: mutuelle-payment-module
 *
 */
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

}
