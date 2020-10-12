package com.example.demo.Repo;

import java.util.List;

import com.example.demo.Model.MedicalTest;


public interface MedicalTestInvoiceRepository {
	MedicalTest findBytesttId(Long testId);
	
	   List<MedicalTest> findAll();

}
