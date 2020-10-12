package com.example.demo.Repo;

import java.util.List;

import com.example.demo.Model.Treatment;

public interface TreatmentInvoiceRepository {
	Treatment findBytreatmentId(Long treatmentId);
	
   List<Treatment> findAll();
}
