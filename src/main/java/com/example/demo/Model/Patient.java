package com.example.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "patient")
public class Patient {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patientId;
	@Size(min = 16, max = 16, message = "Valid National Identification is 16 Digits")
	@NotNull
	@NotBlank(message = "required")
	@Pattern(regexp = "[0-9]*", message = "invalid characters! use numbers only!")
	@Column(name="idnb")
	private String idnb;
	private String names;
	private String gender;
	private String age;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getIdnb() {
		return idnb;
	}

	public void setIdnb(String idnb) {
		this.idnb = idnb;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Patient() {
	}

	public Patient(String Names, String Gender, String Age) {
		super();
		this.names = names;
		this.gender = gender;
		this.age = age;

	}
}
