package com.example.demo.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MedicalTest {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long testId;
	private String testName;
	private Long priceUnit;
	public Long getTestId() {
		return testId;
	}
	public void setTestId(Long testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
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
	
	public MedicalTest() {
	}

}
