package com.example.demo.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Author: chabiman
 * @FileName: MedicalAct.java
 * @Date: Oct 15, 2020
 * @Package: rw.mutuelle.model
 * @ProjectName: mutuelle-payment-module
 *
 */
@Entity
@Table(
		name = "MEDICAL_ACT",
			uniqueConstraints = { @UniqueConstraint(columnNames = { "date", "PATIENT_ID", "MEDICAL_SERVICE_ID" }) })
public class MedicalAct {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/** The date. */
	private LocalDate date;

	/** The amount. */
	private int amount;

	/** The service. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEDICAL_SERVICE_ID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private MedicalService service;

	/** The patient. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PATIENT_ID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Patient patient;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the service
	 */
	public MedicalService getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public void setService(MedicalService service) {
		this.service = service;
	}

	/**
	 * @return the patient
	 */
	public Patient getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MedicalAct [id=");
		builder.append(id);
		builder.append(", date=");
		builder.append(date);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", service=");
		builder.append(service);
		builder.append(", patient=");
		builder.append(patient);
		builder.append("]");
		return builder.toString();
	}

}
