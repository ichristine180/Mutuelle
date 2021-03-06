package com.mutuelle.demo.model;

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


@Entity
@Table(
    name = "MEDICAL_ACT",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "PATIENT_ID", "MEDICAL_SERVICE_ID"})})
public class MedicalAct
{

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /** The date. */
    private LocalDate date;

    /** The service. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEDICAL_SERVICE_ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MedicalService service;

    /** The patient. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Patient patient;

    private long amount;

    public long getId()
    {
        return id;
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(final LocalDate date)
    {
        this.date = date;
    }

    public MedicalService getService()
    {
        return service;
    }

    public void setService(final MedicalService service)
    {
        this.service = service;
    }

    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(final Patient patient)
    {
        this.patient = patient;
    }

    public long getAmount()
    {
        return amount;
    }

    public void setAmount(final long amount)
    {
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("MedicalAct{");
        sb.append("id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", service=").append(service);
        sb.append(", patient=").append(patient);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
