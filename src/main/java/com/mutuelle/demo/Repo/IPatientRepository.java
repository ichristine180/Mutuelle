package com.mutuelle.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.mutuelle.demo.Model.Patient;


@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

	Patient findById(long id);
	Patient findByIdnb(String idnbr);

	
	



	
}
