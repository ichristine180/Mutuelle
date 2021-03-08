package com.mutuelle.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mutuelle.demo.model.HealthFacility;
import com.mutuelle.demo.model.Invoice;
import com.mutuelle.demo.model.Patient;


/**
 * The interface Invoice repository.
 */
@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long>
{
    /**
     * Find by patient list.
     *
     * @param patient the patient
     * @return the list
     */
    List<Invoice> findByPatient(Patient patient);

	List<Invoice> findByHealthFacility(HealthFacility hf);
}