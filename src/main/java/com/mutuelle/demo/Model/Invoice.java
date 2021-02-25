package com.mutuelle.demo.Model;

import javax.persistence.Entity;


import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mutuelle.demo.Model.security.Users;

@Entity
@Table(name = "INVOICE")
public class Invoice {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoice_id;
	private Double patient_Percentage;
	private Double rssb_Percentage;
	private Double total;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patientId")
	private Patient patient;
	@OneToOne(fetch = FetchType.LAZY)
	


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "generatedBy")
	private Users generatedBy;

	
	public Long getInvoice_id() {
		return invoice_id;
	}


	public void setInvoice_id(Long invoice_id) {
		this.invoice_id = invoice_id;
	}


	


	public Double getPatient_Percentage() {
		return patient_Percentage;
	}


	public void setPatient_Percentage(Double patient_Percentage) {
		this.patient_Percentage = patient_Percentage;
	}


	public Double getRssb_Percentage() {
		return rssb_Percentage;
	}


	public void setRssb_Percentage(Double rssb_Percentage) {
		this.rssb_Percentage = rssb_Percentage;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public Users getGeneratedBy() {
		return generatedBy;
	}


	public void setGeneratedBy(Users generatedBy) {
		this.generatedBy = generatedBy;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Invoice() {
	}

}
