package com.mutuelle.demo.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "PAYMENT_LOG")
public class PaymentLog
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long paymentLogId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HEALTH_FACILITY_ID")
    private HealthFacility healthFacility;

    private long totalBalance;

    public long getPaymentLogId()
    {
        return paymentLogId;
    }

    public void setPaymentLogId(final long paymentLogId)
    {
        this.paymentLogId = paymentLogId;
    }

    public HealthFacility getHealthFacility()
    {
        return healthFacility;
    }

    public void setHealthFacility(final HealthFacility healthFacility)
    {
        this.healthFacility = healthFacility;
    }

    public long getTotalBalance()
    {
        return totalBalance;
    }

    public void setTotalBalance(final long totalBalance)
    {
        this.totalBalance = totalBalance;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("PaymentLog{");
        sb.append("paymentLogId=").append(paymentLogId);
        sb.append(", healthFacility=").append(healthFacility);
        sb.append(", totalBalance=").append(totalBalance);
        sb.append('}');
        return sb.toString();
    }
}
