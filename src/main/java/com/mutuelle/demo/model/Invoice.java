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

import com.mutuelle.demo.model.security.Users;


@Entity
@Table(name = "INVOICE")
public class Invoice
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;
    private long patientPayment;
    private long rssbPayment;
    private long total;
    private LocalDate createdOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "generatedBy")
    private Users generatedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HEALTH_FACILITY_ID")
    private HealthFacility healthFacility;

    public Long getInvoiceId()
    {
        return invoiceId;
    }

    public void setInvoiceId(final Long invoice_id)
    {
        this.invoiceId = invoice_id;
    }

    public long getPatientPayment()
    {
        return patientPayment;
    }

    public void setPatientPayment(final long patient_Percentage)
    {
        this.patientPayment = patient_Percentage;
    }

    public long getRssbPayment()
    {
        return rssbPayment;
    }

    public void setRssbPayment(final long rssb_Percentage)
    {
        this.rssbPayment = rssb_Percentage;
    }

    public long getTotal()
    {
        return total;
    }

    public void setTotal(final long total)
    {
        this.total = total;
    }

    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(final Patient patient)
    {
        this.patient = patient;
    }

    public Users getGeneratedBy()
    {
        return generatedBy;
    }

    public void setGeneratedBy(final Users generatedBy)
    {
        this.generatedBy = generatedBy;
    }

    public HealthFacility getHealthFacility()
    {
        return healthFacility;
    }

    public void setHealthFacility(final HealthFacility healthFacility)
    {
        this.healthFacility = healthFacility;
    }

    public LocalDate getCreatedOn()
    {
        return createdOn;
    }

    public void setCreatedOn(final LocalDate createdOn)
    {
        this.createdOn = createdOn;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Invoice{");
        sb.append("invoiceId=").append(invoiceId);
        sb.append(", patientPayment=").append(patientPayment);
        sb.append(", rssbPayment=").append(rssbPayment);
        sb.append(", total=").append(total);
        sb.append(", createdOn=").append(createdOn);
        sb.append(", patient=").append(patient);
        sb.append(", generatedBy=").append(generatedBy);
        sb.append(", healthFacility=").append(healthFacility);
        sb.append('}');
        return sb.toString();
    }
}
