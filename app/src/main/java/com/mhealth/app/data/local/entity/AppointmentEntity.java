package com.mhealth.app.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mhealth.app.data.model.Appointment;

@Entity(tableName = "appointments")
public class AppointmentEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String id;

    @ColumnInfo(name = "patient_id")
    private final String patientId;

    @ColumnInfo(name = "patient_name")
    private final String patientName;

    @ColumnInfo(name = "doctor_id")
    private final String doctorId;

    @ColumnInfo(name = "doctor_name")
    private final String doctorName;

    @ColumnInfo(name = "reason")
    private final String reason;

    @ColumnInfo(name = "date_label")
    private final String dateLabel;

    @ColumnInfo(name = "time_label")
    private final String timeLabel;

    @ColumnInfo(name = "location")
    private final String location;

    @ColumnInfo(name = "status")
    private final String status;

    @ColumnInfo(name = "created_at")
    private final long createdAt;

    @ColumnInfo(name = "owner_secretary_id")
    private final String ownerSecretaryId;

    public AppointmentEntity(
            @NonNull String id,
            String patientId,
            String patientName,
            String doctorId,
            String doctorName,
            String reason,
            String dateLabel,
            String timeLabel,
            String location,
            String status,
            long createdAt,
            String ownerSecretaryId
    ) {
        this.id = id;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.reason = reason;
        this.dateLabel = dateLabel;
        this.timeLabel = timeLabel;
        this.location = location;
        this.status = status;
        this.createdAt = createdAt;
        this.ownerSecretaryId = ownerSecretaryId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getReason() {
        return reason;
    }

    public String getDateLabel() {
        return dateLabel;
    }

    public String getTimeLabel() {
        return timeLabel;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public String getOwnerSecretaryId() {
        return ownerSecretaryId;
    }

    public Appointment toModel() {
        return new Appointment(id, patientName, doctorName, reason, dateLabel, timeLabel);
    }
}

