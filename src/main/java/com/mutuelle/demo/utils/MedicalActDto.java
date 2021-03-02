package com.mutuelle.demo.utils;

import com.mutuelle.demo.model.MedicalService;


public class MedicalActDto
{
    private Double amount;

    private MedicalService service;

    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(final Double amount)
    {
        this.amount = amount;
    }

    public MedicalService getService()
    {
        return service;
    }

    public void setService(final MedicalService service)
    {
        this.service = service;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("MedicalActDto{");
        sb.append(", amount=").append(amount);
        sb.append(", service=").append(service);
        sb.append('}');
        return sb.toString();
    }
}
