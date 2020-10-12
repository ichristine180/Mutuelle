package com.example.demo.service;

import java.util.List;

import com.example.demo.Model.Treatment;

public interface ITreatmentInvoiceService {
	String NAME = "TreatmentInvoiceService";

	Treatment findTreatmentBytreatmentId(Long treatmentId);

	List<Treatment> findAll();


}
