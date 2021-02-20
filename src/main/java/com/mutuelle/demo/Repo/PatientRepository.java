package com.mutuelle.demo.Repo;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.Model.Patient;


@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {
	Patient findByid(String id);

	@Override
	List<Patient> findAll();

}