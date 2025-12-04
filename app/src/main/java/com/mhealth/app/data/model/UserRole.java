package com.mhealth.app.data.model;

public enum UserRole {
    PATIENT,
    DOCTOR,
    ADMIN,
    SECRETARY;

    public static UserRole fromDisplayLabel(String label) {
        if (label == null) {
            return PATIENT;
        }
        switch (label.toLowerCase()) {
            case "patient":
                return PATIENT;
            case "médecin":
            case "medecin":
                return DOCTOR;
            case "administrateur":
                return ADMIN;
            case "secrétaire":
            case "secretaire":
                return SECRETARY;
            default:
                return PATIENT;
        }
    }
}

