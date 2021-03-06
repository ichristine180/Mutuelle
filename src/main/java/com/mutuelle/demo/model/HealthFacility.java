package com.mutuelle.demo.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "health_facility")
public class HealthFacility
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @Column(name = "facilityName")
    @NotEmpty(message = "facility Name can not be null")
    private String facilityName;
    
    @NotEmpty(message = " adress can not be null")
    @Column(name = "adrress")
    private String adrress;

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getFacilityName()
    {
        return facilityName;
    }

    public void setFacilityName(final String facilityName)
    {
        this.facilityName = facilityName;
    }

    public String getAdrress()
    {
        return adrress;
    }

    public void setAdrress(final String adrress)
    {
        this.adrress = adrress;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("HealthFacility{");
        sb.append("id=").append(id);
        sb.append(", facilityName='").append(facilityName).append('\'');
        sb.append(", adrress='").append(adrress).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
