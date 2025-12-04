package com.mhealth.app.data.model;

import java.io.Serializable;
public class Appointment implements Serializable {
    private final String id;
    private final String patientName;
    private final String doctorName;
    private final String reason;
    private final String date;
    private final String time;

    public Appointment(String id, String patientName, String doctorName, String reason, String date, String time) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.reason = reason;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getReason() {
        return reason;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String toDisplayString() {
        return date + " • " + time + " • " + reason;
    }
}

