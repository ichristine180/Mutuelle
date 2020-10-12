package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Treatment;
import com.example.demo.Repo.TreatmentRepository;

@Service
public class TreatmentService implements ITreatmentService {
	@Autowired
	private TreatmentRepository treatmentRepo;

	@Override
	public List<Treatment> findAll() {
		try {
			return treatmentRepo.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Treatment findTreatmentBytreatmentId(Long treatmentId) {
		try {
			return treatmentRepo.findBytreatmentId(treatmentId);
		} catch (Exception e) {
			throw e;
		}
	}

}



