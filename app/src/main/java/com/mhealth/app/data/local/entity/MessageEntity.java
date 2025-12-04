package com.mhealth.app.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mhealth.app.data.model.Message;

@Entity(tableName = "messages")
public class MessageEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String id;

    @ColumnInfo(name = "owner_id")
    private final String ownerId;

    @ColumnInfo(name = "scope")
    private final String scope;

    @ColumnInfo(name = "sender_name")
    private final String senderName;

    @ColumnInfo(name = "receiver_name")
    private final String receiverName;

    @ColumnInfo(name = "subject")
    private final String subject;

    @ColumnInfo(name = "body")
    private final String body;

    @ColumnInfo(name = "timestamp")
    private final long timestamp;

    public MessageEntity(
            @NonNull String id,
            String ownerId,
            String scope,
            String senderName,
            String receiverName,
            String subject,
            String body,
            long timestamp
    ) {
        this.id = id;
        this.ownerId = ownerId;
        this.scope = scope;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.subject = subject;
        this.body = body;
        this.timestamp = timestamp;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getScope() {
        return scope;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public Message toModel() {
        return new Message(senderName, receiverName, subject, body);
    }

    public long getTimestamp() {
        return timestamp;
    }
}

