package com.mutuelle.demo.model;

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
public class MedicalService
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /** The name. */
    private String name;

    /** The unit price. */
    private double unitPrice;

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

    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public double getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(final double unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(final String code)
    {
        this.code = code;
    }

    public EMedicalServiceType getType()
    {
        return type;
    }

    public void setType(final EMedicalServiceType type)
    {
        this.type = type;
    }

    public List<MedicalAct> getMedicalActList()
    {
        return medicalActList;
    }

    public void setMedicalActList(final List<MedicalAct> medicalActList)
    {
        this.medicalActList = medicalActList;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("MedicalService{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", code='").append(code).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
