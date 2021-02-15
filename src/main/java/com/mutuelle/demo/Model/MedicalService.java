package com.mutuelle.demo.Model;

import java.util.ArrayList;




import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mutuelle.demo.utils.EMedicalServiceType;
@Entity
@Table(name = "MEDICAL_SERVICE")
public class MedicalService {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/** The name. */
	private String name;

	/** The unit price. */
	private Double unitPrice;

	/** The code. */
	@NotNull
	@Column(unique = true)
	private String code;

	/** The type. */
	@Enumerated(EnumType.STRING)
	private EMedicalServiceType type;

	/** The medical act list. */
	@JsonIgnore
	@OneToMany(mappedBy = "service")
	private List<MedicalAct> medicalActList = new ArrayList<>();

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
	 * @return the medicalActList
	 */
	public List<MedicalAct> getMedicalActList() {
		return medicalActList;
	}

	/**
	 * @param medicalActList the medicalActList to set
	 */
	public void setMedicalActList(List<MedicalAct> medicalActList) {
		this.medicalActList = medicalActList;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the unitPrice
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the type
	 */
	public EMedicalServiceType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(EMedicalServiceType type) {
		this.type = type;
	}

	/*
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MedicalService [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", unitPrice=");
		builder.append(unitPrice);
		builder.append(", code=");
		builder.append(code);
		builder.append(", type=");
		builder.append(type);
		return builder.toString();
	}

}
