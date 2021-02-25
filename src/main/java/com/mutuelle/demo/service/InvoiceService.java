package com.mutuelle.demo.service;

import java.time.LocalDate;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutuelle.demo.Model.Invoice;
import com.mutuelle.demo.Model.MedicalAct;
import com.mutuelle.demo.Model.Patient;
import com.mutuelle.demo.Repo.InvoiceRepository;

@Service(IInvoiceService.NAME)
public class InvoiceService implements IInvoiceService {

	@Autowired
	private IMedicalActService mActService;
	@Autowired
	private InvoiceRepository iRepository;

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

	@Override
	public Invoice createMedicalAct(Invoice invoice) {
		try {
			return iRepository.save(invoice);
		} catch (Exception ex) {
			throw ex;
		}
	}
}
