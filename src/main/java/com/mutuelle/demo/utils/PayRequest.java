package com.mutuelle.demo.utils;

public class PayRequest
{

    private long amount;

    private long centerId;

    public long getAmount()
    {
        return amount;
    }

    public void setAmount(final long amount)
    {
        this.amount = amount;
    }

    public long getCenterId()
    {
        return centerId;
    }

    public void setCenterId(final long centerId)
    {
        this.centerId = centerId;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("PayRequest{");
        sb.append("amount=").append(amount);
        sb.append(", centerId=").append(centerId);
        sb.append('}');
        return sb.toString();
    }
}
