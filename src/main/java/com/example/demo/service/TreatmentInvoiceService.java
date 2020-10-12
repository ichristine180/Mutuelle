package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Model.Treatment;
import com.example.demo.Repo.TreatmentRepository;

public class TreatmentInvoiceService implements ITreatmentInvoiceService {
	@Autowired
	private TreatmentRepository treatmentRepo;

	public Treatment findTreatmentBytreatmentId(long treatmentId) {
		try {
			return treatmentRepo.findBytreatmentId(treatmentId);
		} catch (Exception e) {
			throw e;
		}

	}
	
	public List<Treatment> findTreatment() {
		try {
			return treatmentRepo.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Treatment findTreatmentBytreatmentId(Long treatmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Treatment> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
