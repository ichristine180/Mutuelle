package com.example.demo.service;

import java.util.List;

import com.example.demo.Model.MedicalTest;

public interface IMedicalTestService {
	String NAME = "MedicalTestService";

	MedicalTest findMedicalTestBytestId(Long testId);

	List<MedicalTest> findAll();

	MedicalTest findMedicalTestBytestId(long testId);

}


