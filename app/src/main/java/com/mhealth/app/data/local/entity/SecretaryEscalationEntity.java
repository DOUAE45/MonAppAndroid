package com.mhealth.app.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "secretary_escalations")
public class SecretaryEscalationEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String id;

    @ColumnInfo(name = "secretary_id")
    private final String secretaryId;

    @ColumnInfo(name = "content")
    private final String content;

    @ColumnInfo(name = "timestamp")
    private final long timestamp;

    public SecretaryEscalationEntity(
            @NonNull String id,
            String secretaryId,
            String content,
            long timestamp
    ) {
        this.id = id;
        this.secretaryId = secretaryId;
        this.content = content;
        this.timestamp = timestamp;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getSecretaryId() {
        return secretaryId;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

