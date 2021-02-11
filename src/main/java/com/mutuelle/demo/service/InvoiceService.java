package com.mutuelle.demo.service;

import java.time.LocalDate;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.Model.MedicalAct;
import com.mutuelle.demo.Model.Patient;

@Service(IInvoiceService.NAME)
public class InvoiceService implements IInvoiceService {

	@Autowired
	private IMedicalActService mActService;

	/*
	 *
	 * @see rw.mutuelle.service.IInvoiceService#calculateInvoiceAmount(rw.mutuelle.model.MedicalAct)
	 */
	@Override
	public double calculateInvoiceAmount(Patient patient, LocalDate date) {
		try {
			double amount = 0;
			List<MedicalAct> medicalActList = mActService.findOneDateActByPatient(patient, date);
			for (MedicalAct act : medicalActList) {
				double actPrice = act.getAmount() * act.getService().getUnitPrice();
				amount += actPrice;
			}
			return amount;
		} catch (Exception ex) {
			throw ex;
		}
	}
}
