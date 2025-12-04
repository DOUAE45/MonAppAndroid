package com.mhealth.app.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mhealth.app.data.model.MedicalResult;

@Entity(tableName = "medical_results")
public class MedicalResultEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String id;

    @ColumnInfo(name = "doctor_id")
    private final String doctorId;

    @ColumnInfo(name = "type")
    private final String type;

    @ColumnInfo(name = "summary")
    private final String summary;

    @ColumnInfo(name = "status")
    private final String status;

    @ColumnInfo(name = "created_at")
    private final long createdAt;

    public MedicalResultEntity(
            @NonNull String id,
            String doctorId,
            String type,
            String summary,
            String status,
            long createdAt
    ) {
        this.id = id;
        this.doctorId = doctorId;
        this.type = type;
        this.summary = summary;
        this.status = status;
        this.createdAt = createdAt;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getType() {
        return type;
    }

    public String getSummary() {
        return summary;
    }

    public String getStatus() {
        return status;
    }

    public MedicalResult toModel() {
        return new MedicalResult(id, type, summary, status);
    }

    public long getCreatedAt() {
        return createdAt;
    }
}

