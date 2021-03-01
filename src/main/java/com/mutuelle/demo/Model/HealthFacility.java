package com.mutuelle.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "health_facility")
public class HealthFacility
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "facilityName")
    private String facilityName;
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

}
