package com.mutuelle.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.model.Patient;
import com.mutuelle.demo.repository.IPatientRepository;


@Service(IPatientService.NAME)
@Transactional
public class PatientService implements IPatientService
{

    /** The patient repository. */
    @Autowired
    private IPatientRepository patientRepository;

    /*
     *
     * @see rw.mutuelle.service.IPatientService#createPatient(rw.mutuelle.model.Patient)
     */
    @Override
    public Patient createPatient(final Patient patient)
    {
        try
        {
            return patientRepository.save(patient);
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }

    /*
     *
     * @see rw.mutuelle.service.IPatientService#findAllPatient()
     */
    @Override
    public List<Patient> findAllPatient()
    {
        try
        {
            return patientRepository.findAll();
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }

    /*
     *
     * @see rw.mutuelle.service.IPatientService#findPatientById(java.lang.Long)
     */
    @Override
    public Patient findPatientById(final Long id)
    {
        try
        {
            return patientRepository.findById(id);
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }

    public List<Patient> listAll()
    {
        return patientRepository.findAll();
    }

    public void save(final Patient pat)
    {
        patientRepository.save(pat);
    }

    @Override
    public Patient findPatientByIdnbr(final String idnbr)
    {
        // TODO Auto-generated method stub
        try
        {
            return patientRepository.findByIdnb(idnbr);
        }
        catch (final Exception e)
        {
            // TODO: handle exception
            throw e;
        }
    }

}

