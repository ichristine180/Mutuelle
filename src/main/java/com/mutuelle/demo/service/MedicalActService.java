package com.mutuelle.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.model.MedicalAct;
import com.mutuelle.demo.model.Patient;
import com.mutuelle.demo.repository.IMedicalActRepository;


@Service(IMedicalActService.NAME)
public class MedicalActService implements IMedicalActService
{

    @Autowired
    private IMedicalActRepository medicalActRepository;

    /*
     *
     * @see rw.mutuelle.service.IMedicalActService#createMedicalAct(rw.mutuelle.model.MedicalAct)
     */
    @Override
    public MedicalAct createMedicalAct(final MedicalAct medicalAct)
    {
        try
        {
            return medicalActRepository.save(medicalAct);
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }

    /*
     *
     * @see rw.mutuelle.service.IMedicalActService#findAllActByPatient(rw.mutuelle.model.Patient)
     */
    @Override
    public List<MedicalAct> findAllActByPatient(final Patient patient)
    {
        try
        {
            return medicalActRepository.findByPatient(patient);
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }

    /*
     *
     * @see rw.mutuelle.service.IMedicalActService#findOneDateActByPatient(rw.mutuelle.model.Patient,
     * java.time.LocalDate)
     */

    @Override
	public List<MedicalAct> findOneDateActByPatient(final Patient patient, final LocalDate date)
    {
        try
        {
            return medicalActRepository.findByPatientAndDate(patient, date);
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }

}
