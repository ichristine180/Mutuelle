package com.example.demo.service;

import java.util.List;

import com.example.demo.Model.Treatment;

public interface ITreatmentService {
	String NAME = "TreatmentService";

	Treatment findTreatmentBytreatmentId(Long treatmentId);

	List<Treatment> findAll();

}



