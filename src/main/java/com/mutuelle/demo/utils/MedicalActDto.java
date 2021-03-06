package com.mutuelle.demo.utils;

import com.mutuelle.demo.model.MedicalService;


public class MedicalActDto
{
    private long amount;

    private MedicalService service;

    public MedicalService getService()
    {
        return service;
    }

    public void setService(final MedicalService service)
    {
        this.service = service;
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
        final StringBuilder sb = new StringBuilder("MedicalActDto{");
        sb.append("amount=").append(amount);
        sb.append(", service=").append(service);
        sb.append('}');
        return sb.toString();
    }
}
