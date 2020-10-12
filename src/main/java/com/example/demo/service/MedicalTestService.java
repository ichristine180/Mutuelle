package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.MedicalTest;
import com.example.demo.Model.Treatment;
import com.example.demo.Repo.MedicalTestRepository;


@Service
public class MedicalTestService implements IMedicalTestService{
	@Autowired
	private MedicalTestRepository testRepo;
	
	@Override
    public MedicalTest findMedicalTestBytestId(long testId) {
		try {
			return testRepo.findBytestId(testId);
		} catch (Exception e) {
			throw e;
		}

	}
	@Override
	public List<MedicalTest> findAll() {
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
	
	


}
