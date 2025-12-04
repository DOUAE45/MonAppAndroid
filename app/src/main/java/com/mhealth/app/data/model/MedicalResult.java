package com.mhealth.app.data.model;

import java.io.Serializable;

public class MedicalResult implements Serializable {
    private final String id;
    private final String type;
    private final String summary;
    private final String status;

    public MedicalResult(String id, String type, String summary, String status) {
        this.id = id;
        this.type = type;
        this.summary = summary;
        this.status = status;
    }

    public String getId() {
        return id;
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

    public String toDisplayString() {
        return type + " (" + status + ") - " + summary;
    }
}

