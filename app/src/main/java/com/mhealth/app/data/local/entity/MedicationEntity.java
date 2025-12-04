package com.mhealth.app.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mhealth.app.data.model.Medication;

@Entity(tableName = "medications")
public class MedicationEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String id;

    @ColumnInfo(name = "patient_id")
    private final String patientId;

    @ColumnInfo(name = "name")
    private final String name;

    @ColumnInfo(name = "dosage")
    private final String dosage;

    @ColumnInfo(name = "frequency")
    private final String frequency;

    @ColumnInfo(name = "status")
    private final String status;

    public MedicationEntity(
            @NonNull String id,
            String patientId,
            String name,
            String dosage,
            String frequency,
            String status
    ) {
        this.id = id;
        this.patientId = patientId;
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.status = status;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public Medication toModel() {
        return new Medication(name, dosage, frequency);
    }

    public String getStatus() {
        return status;
    }
}

