package com.mutuelle.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mutuelle.demo.model.security.Users;


@Entity
@Table(name = "PAYMENT_TRANSACTION")
public class PaymentTransaction
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long txtId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HEALTH_FACILITY_ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private HealthFacility healthFacility;

    private LocalDateTime paymentDate;

    private long amountPaid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Users processedBy;

    public long getTxtId()
    {
        return txtId;
    }

    public void setTxtId(final long txtId)
    {
        this.txtId = txtId;
    }

    public HealthFacility getHealthFacility()
    {
        return healthFacility;
    }

    public void setHealthFacility(final HealthFacility healthFacility)
    {
        this.healthFacility = healthFacility;
    }

    public LocalDateTime getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate(final LocalDateTime paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    public long getAmountPaid()
    {
        return amountPaid;
    }

    public void setAmountPaid(final long amountPaid)
    {
        this.amountPaid = amountPaid;
    }

    public Users getProcessedBy()
    {
        return processedBy;
    }

    public void setProcessedBy(final Users processedBy)
    {
        this.processedBy = processedBy;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("PaymentTransaction{");
        sb.append("txtId=").append(txtId);
        sb.append(", healthFacility=").append(healthFacility);
        sb.append(", paymentDate=").append(paymentDate);
        sb.append(", amountPaid=").append(amountPaid);
        sb.append(", processedBy=").append(processedBy);
        sb.append('}');
        return sb.toString();
    }
}
