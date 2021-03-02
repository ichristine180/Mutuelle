package com.mutuelle.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.model.Patient;


@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long>
{

    Patient findById(long id);

    Patient findByIdnb(String idnbr);
}
