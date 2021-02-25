package com.mutuelle.demo.service;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.mutuelle.demo.Model.Invoice;
import com.mutuelle.demo.Model.Patient;



public interface IInvoiceService {

	/** The name. */
	String NAME = "invoiceService";

	/**
	 * Calculate invoice amount.
	 *
	 * @param patient the patient
	 * @param date    the date
	 * @return the double
	 */
	double calculateInvoiceAmount(Patient patient, LocalDate date);
	public Invoice createMedicalAct(@NotNull Invoice invoice);

}
