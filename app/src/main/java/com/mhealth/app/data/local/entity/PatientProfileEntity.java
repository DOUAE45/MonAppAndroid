package com.mhealth.app.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mhealth.app.data.model.PatientProfile;

@Entity(tableName = "patient_profiles")
public class PatientProfileEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String id;

    @ColumnInfo(name = "full_name")
    private final String fullName;

    @ColumnInfo(name = "medical_id")
    private final String medicalId;

    @ColumnInfo(name = "insurance")
    private final String insurance;

    @ColumnInfo(name = "primary_doctor_id")
    private final String primaryDoctorId;

    @ColumnInfo(name = "primary_doctor_name")
    private final String primaryDoctorName;

    public PatientProfileEntity(
            @NonNull String id,
            String fullName,
            String medicalId,
            String insurance,
            String primaryDoctorId,
            String primaryDoctorName
    ) {
        this.id = id;
        this.fullName = fullName;
        this.medicalId = medicalId;
        this.insurance = insurance;
        this.primaryDoctorId = primaryDoctorId;
        this.primaryDoctorName = primaryDoctorName;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMedicalId() {
        return medicalId;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getPrimaryDoctorId() {
        return primaryDoctorId;
    }

    public String getPrimaryDoctorName() {
        return primaryDoctorName;
    }

    public PatientProfile toModel() {
        return new PatientProfile(fullName, medicalId, insurance, primaryDoctorName);
    }
}

