package com.mutuelle.demo.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mutuelle.demo.Model.security.Users;


@Entity
@Table(name = "INVOICE")
public class Invoice
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoice_id;
    private long patient_Percentage;
    private long rssb_Percentage;
    private long total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "generatedBy")
    private Users generatedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HEALTH_FACILITY_ID")
    private HealthFacility healthFacility;

    public Long getInvoice_id()
    {
        return invoice_id;
    }

    public void setInvoice_id(final Long invoice_id)
    {
        this.invoice_id = invoice_id;
    }

    public long getPatient_Percentage()
    {
        return patient_Percentage;
    }

    public void setPatient_Percentage(final long patient_Percentage)
    {
        this.patient_Percentage = patient_Percentage;
    }

    public long getRssb_Percentage()
    {
        return rssb_Percentage;
    }

    public void setRssb_Percentage(final long rssb_Percentage)
    {
        this.rssb_Percentage = rssb_Percentage;
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

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Invoice{");
        sb.append("invoice_id=").append(invoice_id);
        sb.append(", patient_Percentage=").append(patient_Percentage);
        sb.append(", rssb_Percentage=").append(rssb_Percentage);
        sb.append(", total=").append(total);
        sb.append(", patient=").append(patient);
        sb.append(", generatedBy=").append(generatedBy);
        sb.append(", healthFacility=").append(healthFacility);
        sb.append('}');
        return sb.toString();
    }
}
