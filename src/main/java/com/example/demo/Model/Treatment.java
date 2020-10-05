package com.example.demo.Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Treatment {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long treatmentId;
	private String treatmentName;
	private Long priceUnit;
	public Long getTreatmentId() {
		return treatmentId;
	}
	public void setTreatmentId(Long treatmentId) {
		this.treatmentId = treatmentId;
	}
	public String getTreatmentName() {
		return treatmentName;
	}
	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}
	public Long getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(Long priceUnit) {
		this.priceUnit = priceUnit;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Treatment() {
	}
}