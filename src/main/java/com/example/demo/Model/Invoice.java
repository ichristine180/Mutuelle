package com.example.demo.Model;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.Model.security.Users;

@Entity
@Table(name = "INVOICE")
public class Invoice {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long invoice_id;
	private Long payment_code;
	private Long patient_Percentage;
	private Long rssb_Percentage;
	private Long total;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "invoice")
	private Payment payment;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_Id")
	private Patient patient;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "generatedBy")
	private Users generatedBy;

	public Users getGeneratedBy() {
		return generatedBy;
	}

	public void setGeneratedBy(Users generatedBy) {
		this.generatedBy = generatedBy;
	}

	public Long getInvoice_id() {
		return invoice_id;
	}

	public void setInvoice_id(Long invoice_id) {
		this.invoice_id = invoice_id;
	}

	public Long getPayment_code() {
		return payment_code;
	}

	public void setPayment_code(Long payment_code) {
		this.payment_code = payment_code;
	}

	public Long getPatient_Percentage() {
		return patient_Percentage;
	}

	public void setPatient_Percentage(Long patient_Percentage) {
		this.patient_Percentage = patient_Percentage;
	}

	public Long getRssb_Percentage() {
		return rssb_Percentage;
	}

	public void setRssb_Percentage(Long rssb_Percentage) {
		this.rssb_Percentage = rssb_Percentage;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Invoice() {
	}

	
}
