package com.mutuelle.demo.utils;

import java.util.List;


public class PrescriptionData
{
    private List<MedicalActDto> medicalActDtoList;

    private long patientId;

    private long processedBy;

    private long processedAt;

    public long getProcessedBy()
    {
        return processedBy;
    }

    public void setProcessedBy(final long processedBy)
    {
        this.processedBy = processedBy;
    }

    public long getProcessedAt()
    {
        return processedAt;
    }

    public void setProcessedAt(final long processedAt)
    {
        this.processedAt = processedAt;
    }

    public long getPatientId()
    {
        return patientId;
    }

    public void setPatientId(final long patientId)
    {
        this.patientId = patientId;
    }

    public List<MedicalActDto> getMedicalActDtoList()
    {
        return medicalActDtoList;
    }

    public void setMedicalActDtoList(final List<MedicalActDto> medicalActDtoList)
    {
        this.medicalActDtoList = medicalActDtoList;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("PrescriptionData{");
        sb.append("medicalActDtoList=").append(medicalActDtoList);
        sb.append(", patientId=").append(patientId);
        sb.append(", processedBy=").append(processedBy);
        sb.append(", processedAt=").append(processedAt);
        sb.append('}');
        return sb.toString();
    }
}
