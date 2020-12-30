package com.example.demo.service;

import java.time.LocalDate;

import com.example.demo.Model.Patient;




/**
 * @Author: chabiman
 * @FileName: IIncoiveService.java
 * @Date: Oct 16, 2020
 * @Package: rw.mutuelle.service
 * @ProjectName: mutuelle-payment-module
 *
 */
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

}
