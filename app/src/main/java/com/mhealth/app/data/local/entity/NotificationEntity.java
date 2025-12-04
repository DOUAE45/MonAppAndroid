package com.mhealth.app.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_notifications")
public class NotificationEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String id;

    @ColumnInfo(name = "patient_id")
    private final String patientId;

    @ColumnInfo(name = "content")
    private final String content;

    @ColumnInfo(name = "timestamp")
    private final long timestamp;

    public NotificationEntity(
            @NonNull String id,
            String patientId,
            String content,
            long timestamp
    ) {
        this.id = id;
        this.patientId = patientId;
        this.content = content;
        this.timestamp = timestamp;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

