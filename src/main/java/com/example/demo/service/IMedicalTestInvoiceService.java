package com.example.demo.service;

import java.util.List;

import com.example.demo.Model.MedicalTest;


public interface IMedicalTestInvoiceService {
	String NAME = "MedicalTestInvoiceService";

	MedicalTest findMedicalTestBytestId(Long testId);

	List<MedicalTest> findAll();


}
