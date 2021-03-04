package com.mutuelle.demo.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.mutuelle.demo.model.Invoice;
import com.mutuelle.demo.model.MedicalAct;
import com.mutuelle.demo.model.Patient;
import com.mutuelle.demo.model.security.Users;
import com.mutuelle.demo.utils.DetailedInvoice;


/**
 * The interface Invoice service.
 */
public interface IInvoiceService
{

    /** The name. */
    String NAME = "invoiceService";

    /**
     * Calculate invoice amount.
     *
     * @param patient the patient
     * @param date the date
     * @return the double
     */
    long calculateInvoiceAmount(Patient patient, LocalDate date);

    /**
     * Create medical act invoice.
     *
     * @param invoice the invoice
     * @return the invoice
     */
    Invoice createInvoice(@NotNull Invoice invoice);

    /**
     * Save patient treatement transaction detailed invoice.
     *
     * @param treatementList the treatement list
     * @param patient the patient
     * @param processedBy the processed by
     * @return the detailed invoice
     */
    DetailedInvoice savePatientTreatementTransaction(final
                                                     List<MedicalAct> treatementList,
                                                     final Patient patient,
                                                     final Users processedBy);

    /**
     * Find invoice list.
     *
     * @param patient the patient
     * @return the list
     */
    List<Invoice> findInvoice(Patient patient);
}
