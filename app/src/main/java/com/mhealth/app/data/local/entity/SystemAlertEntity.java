package com.mhealth.app.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "system_alerts")
public class SystemAlertEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String id;

    @ColumnInfo(name = "content")
    private final String content;

    @ColumnInfo(name = "timestamp")
    private final long timestamp;

    public SystemAlertEntity(
            @NonNull String id,
            String content,
            long timestamp
    ) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}

