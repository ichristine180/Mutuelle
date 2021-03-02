package com.mutuelle.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.model.MedicalService;
import com.mutuelle.demo.repository.IMedicalServiceRepository;
import com.mutuelle.demo.utils.EMedicalServiceType;


@Service(IMedicalServiceService.NAME)
public class MedicalServiceService implements IMedicalServiceService
{

    /** The medical service repository. */
    @Autowired
    private IMedicalServiceRepository medicalServiceRepository;

    /*
     *
     * @see rw.mutuelle.service.IMedicalServiceService#createMedicalService(rw.mutuelle.model.MedicalService)
     */
    @Override
    public MedicalService createMedicalService(final MedicalService medicalService)
    {
        try
        {
            return medicalServiceRepository.save(medicalService);
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }

    /*
     *
     * @see rw.mutuelle.service.IMedicalServiceService#findAll()
     */
    @Override
    public List<MedicalService> findAll()
    {
        try
        {
            return medicalServiceRepository.findAll();
        }
        catch (final Exception ex)
        {
            throw ex;
        }
    }

    @Override
    public List<MedicalService> findExams()
    {
        try
        {
            return medicalServiceRepository.findExams();
        }
        catch (final Exception e)
        {
            // TODO: handle exception
            throw e;
        }
    }

    @Override
    public List<MedicalService> findMedicaments(final EMedicalServiceType type)
    {
        try
        {
            return medicalServiceRepository.findByType(type);
        }
        catch (final Exception e)
        {
            // TODO: handle exception
            throw e;
        }
    }

    @Override
    public MedicalService findById(final Long id)
    {
        try
        {
            return medicalServiceRepository.findOne(id);
        }
        catch (final Exception e)
        {
            // TODO: handle exception
            throw e;
        }
    }

    @Override
    public List<MedicalService> findByType(final EMedicalServiceType consultation)
    {
        try
        {
            return medicalServiceRepository.findByType(consultation);
        }
        catch (final Exception e)
        {
            // TODO: handle exception
            throw e;
        }
    }

}
