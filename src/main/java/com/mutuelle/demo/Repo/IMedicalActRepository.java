package com.mutuelle.demo.Repo;

import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.Model.MedicalAct;
import com.mutuelle.demo.Model.MedicalService;
import com.mutuelle.demo.Model.Patient;

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
	List<MedicalAct> findByPatientAndDateAndService(Patient patient,MedicalService medicalService,LocalDate date);


}
