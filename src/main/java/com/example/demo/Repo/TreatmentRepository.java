package com.example.demo.Repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Patient;
import com.example.demo.Model.Treatment;

@Repository
public interface TreatmentRepository extends CrudRepository<Treatment, Long> {
	Treatment findBytreatmentId(Long treatmentId);

	@Override
	List<Treatment> findAll();

}