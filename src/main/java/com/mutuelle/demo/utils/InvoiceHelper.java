package com.mutuelle.demo.utils;

import java.util.List;


public class InvoiceHelper
{
    private List<MedicalActDto> medicalActList;
    
    private long patientId;

    public long getPatientId()
    {
        return patientId;
    }

    public void setPatientId(final long patientId)
    {
        this.patientId = patientId;
    }

    public List<MedicalActDto> getMedicalActList()
    {
        return medicalActList;
    }

    public void setMedicalActList(final List<MedicalActDto> medicalActList)
    {
        this.medicalActList = medicalActList;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("InvoiceHelper{");
        sb.append("medicalActList=").append(medicalActList);
        sb.append(", patientId=").append(patientId);
        sb.append('}');
        return sb.toString();
    }
}
