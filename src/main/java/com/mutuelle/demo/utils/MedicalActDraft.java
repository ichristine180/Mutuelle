package com.mutuelle.demo.utils;

import java.util.List;

import com.mutuelle.demo.model.MedicalService;


public class MedicalActDraft
{
    private List<MedicalService> consultationServiceList;

    private List<MedicalService> laboratoryServiceList;

    private List<MedicalService> drugList;

    private long patientId;

    public List<MedicalService> getConsultationServiceList()
    {
        return consultationServiceList;
    }

    public void setConsultationServiceList(final List<MedicalService> consultationServiceList)
    {
        this.consultationServiceList = consultationServiceList;
    }

    public List<MedicalService> getLaboratoryServiceList()
    {
        return laboratoryServiceList;
    }

    public void setLaboratoryServiceList(final List<MedicalService> laboratoryServiceList)
    {
        this.laboratoryServiceList = laboratoryServiceList;
    }

    public List<MedicalService> getDrugList()
    {
        return drugList;
    }

    public void setDrugList(final List<MedicalService> drugList)
    {
        this.drugList = drugList;
    }

    public long getPatientId()
    {
        return patientId;
    }

    public void setPatientId(final long patientId)
    {
        this.patientId = patientId;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("MedicalActDraft{");
        sb.append("consultationServiceList=").append(consultationServiceList);
        sb.append(", laboratoryServiceList=").append(laboratoryServiceList);
        sb.append(", drugList=").append(drugList);
        sb.append(", patientId=").append(patientId);
        sb.append('}');
        return sb.toString();
    }
}
