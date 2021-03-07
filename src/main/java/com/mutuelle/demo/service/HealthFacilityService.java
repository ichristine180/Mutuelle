package com.mutuelle.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.model.HealthFacility;
import com.mutuelle.demo.repository.IhealthFacilityRepository;


@Service(IHealthFacilityService.NAME)
@Transactional
public class HealthFacilityService implements IHealthFacilityService
{
    @Autowired
    private IhealthFacilityRepository hRepo;

    @Override
    public List<HealthFacility> findAllHealthFacilities()
    {
        try
        {
            return hRepo.findAll();
        }
        catch (final Exception e)
        {
            throw e;
        }

    }

    @Override
    public HealthFacility findOne(final long id)
    {
        // TODO Auto-generated method stub
        try
        {
            return hRepo.findOne(id);
        }
        catch (final Exception e)
        {
            throw e;
        }

    }

    @Override
    public HealthFacility create(final HealthFacility hf)
    {
        // TODO Auto-generated method stub
        try
        {
            return hRepo.save(hf);
        }
        catch (final Exception e)
        {
            throw e;
        }

    }

    @Override
    public HealthFacility findByName(final String name)
    {
        try
        {
            return hRepo.findByFacilityName(name);
        }
        catch (final Exception e)
        {
            throw e;
        }
    }

}
