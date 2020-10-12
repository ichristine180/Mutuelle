package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Model.MedicalTest;
import com.example.demo.Model.Treatment;
import com.example.demo.Repo.MedicalTestRepository;

public class MedicalTestInvoiceService implements IMedicalTestInvoiceService {
	@Autowired
	private MedicalTestRepository testRepo;

	public MedicalTest findMedicalTestBytestId(long testId) {
		try {
			return testRepo.findBytestId(testId);
		} catch (Exception e) {
			throw e;
		}

	}
	
	public List<MedicalTest> findMedicalTest() {
		try {
			return testRepo.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public MedicalTest findMedicalTestBytestId(Long testId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedicalTest> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	}

	
	

	
	



