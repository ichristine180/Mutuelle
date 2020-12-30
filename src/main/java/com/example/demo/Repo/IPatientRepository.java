package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.demo.Model.Patient;




/**
 * @Author: chabiman
 * @FileName: IPatientDao.java
 * @Date: Oct 15, 2020
 * @Package: rw.mutuelle.dao
 * @ProjectName: mutuelle-payment-module
 *
 */
@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

	Patient findById(long id);

	
	



	
}
