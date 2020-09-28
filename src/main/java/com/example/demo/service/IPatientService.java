package com.example.demo.service;

import java.util.List;

import com.example.demo.Model.Patient;

public interface IPatientService {
	String NAME = "PatientService";

	Patient findPatientByidnb(String idnb);

	List<Patient> findAll();

}
