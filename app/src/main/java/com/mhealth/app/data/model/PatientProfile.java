package com.mhealth.app.data.model;

import java.io.Serializable;

public class PatientProfile implements Serializable {
    private final String fullName;
    private final String medicalId;
    private final String insurance;
    private final String primaryDoctor;

    public PatientProfile(String fullName, String medicalId, String insurance, String primaryDoctor) {
        this.fullName = fullName;
        this.medicalId = medicalId;
        this.insurance = insurance;
        this.primaryDoctor = primaryDoctor;
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

    public String getPrimaryDoctor() {
        return primaryDoctor;
    }
}

