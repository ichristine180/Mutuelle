package com.mutuelle.demo.utils;

import java.time.LocalDate;
import java.util.List;

import com.mutuelle.demo.model.HealthFacility;
import com.mutuelle.demo.model.MedicalAct;
import com.mutuelle.demo.model.Patient;


public class DetailedInvoice
{

    private Patient patient;

    private String invoiceNumber;

    private HealthFacility healthFacility;

    private List<MedicalAct> treatementList;

    private LocalDate treatedOn;

    private long totalAmount;

    private long patientAmount;

    private long insuranceAmount;

    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(final Patient patient)
    {
        this.patient = patient;
    }

    public HealthFacility getHealthFacility()
    {
        return healthFacility;
    }

    public void setHealthFacility(final HealthFacility healthFacility)
    {
        this.healthFacility = healthFacility;
    }

    public List<MedicalAct> getTreatementList()
    {
        return treatementList;
    }

    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public void setInvoiceNumber(final String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    public void setTreatementList(final List<MedicalAct> treatementList)
    {
        this.treatementList = treatementList;
    }

    public LocalDate getTreatedOn()
    {
        return treatedOn;
    }

    public void setTreatedOn(final LocalDate treatedOn)
    {
        this.treatedOn = treatedOn;
    }

    public long getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(final long totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public long getPatientAmount()
    {
        return patientAmount;
    }

    public void setPatientAmount(final long patientAmount)
    {
        this.patientAmount = patientAmount;
    }

    public long getInsuranceAmount()
    {
        return insuranceAmount;
    }

    public void setInsuranceAmount(final long insuranceAmount)
    {
        this.insuranceAmount = insuranceAmount;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("DetailedInvoice{");
        sb.append("patient=").append(patient);
        sb.append(", invoiceNumber='").append(invoiceNumber).append('\'');
        sb.append(", healthFacility=").append(healthFacility);
        sb.append(", treatementList=").append(treatementList);
        sb.append(", treatedOn=").append(treatedOn);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", patientAmount=").append(patientAmount);
        sb.append(", insuranceAmount=").append(insuranceAmount);
        sb.append('}');
        return sb.toString();
    }
}
