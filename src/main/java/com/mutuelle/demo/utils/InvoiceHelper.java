package com.mutuelle.demo.utils;

import java.util.List;

import com.mutuelle.demo.model.MedicalAct;


public class InvoiceHelper
{
    List<MedicalAct> medicalActList;

    long patientId;

    public List<MedicalAct> getMedicalActList()
    {
        return medicalActList;
    }

    public void setMedicalActList(final List<MedicalAct> medicalActList)
    {
        this.medicalActList = medicalActList;
    }

    public long getPatientId()
    {
        return patientId;
    }

    public void setPatientId(final long patientId)
    {
        this.patientId = patientId;
    }
}
