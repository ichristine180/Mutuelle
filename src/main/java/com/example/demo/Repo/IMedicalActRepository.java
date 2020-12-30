package com.example.demo.Repo;

import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.MedicalAct;
import com.example.demo.Model.Patient;



/**
 * @Author: chabiman
 * @FileName: IMedicalActDao.java
 * @Date: Oct 15, 2020
 * @Package: rw.mutuelle.dao
 * @ProjectName: mutuelle-payment-module
 *
 */
@Repository
public interface IMedicalActRepository extends JpaRepository<MedicalAct, Long> {

	/**
	 * Find by patient.
	 *
	 * @param patient the patient
	 * @return the list
	 */
	List<MedicalAct> findByPatient(Patient patient);

	/**
	 * Find by patient and date.
	 *
	 * @param patient the patient
	 * @param date    the date
	 * @return the list
	 */
	List<MedicalAct> findByPatientAndDate(Patient patient, LocalDate date);

}
