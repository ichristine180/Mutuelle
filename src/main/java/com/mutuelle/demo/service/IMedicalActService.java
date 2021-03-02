package com.mutuelle.demo.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.mutuelle.demo.model.MedicalAct;
import com.mutuelle.demo.model.Patient;


public interface IMedicalActService
{

    String NAME = "medicalActService";

    /**
     * Creates the medical act.
     *
     * @param medicalAct the medical act
     * @return the medical act
     */
    public MedicalAct createMedicalAct(@NotNull MedicalAct medicalAct);

    /**
     * Find all act by patient.
     *
     * @param patient the patient
     * @return the list
     */
    public List<MedicalAct> findAllActByPatient(@NotNull Patient patient);

    /**
     * Find one date act by patient.
     *
     * @param patient the patient
     * @param date    the date
     * @return the list
     */

    //public List<MedicalAct> findOneDateActPatientByService(@NotNull Patient patient, @NotNull LocalDate date);
    public List<MedicalAct> findOneDateActByPatient(Patient patient, LocalDate date);

}
