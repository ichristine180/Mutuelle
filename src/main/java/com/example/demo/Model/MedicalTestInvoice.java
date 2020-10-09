package com.example.demo.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class MedicalTestInvoice {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medicalTestId;
	private Long patientId;
	private Long amount;
	public Long getMedicalTestId() {
		return medicalTestId;
	}
	public void setMedicalTestId(Long medicalTestId) {
		this.medicalTestId = medicalTestId;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
        public MedicalTestInvoice() {
	
}
}
