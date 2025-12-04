package com.mhealth.app.data.model;

import java.io.Serializable;

public class Medication implements Serializable {
    private final String name;
    private final String dosage;
    private final String frequency;

    public Medication(String name, String dosage, String frequency) {
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
    }

    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public String toDisplayString() {
        return name + " - " + dosage + " • " + frequency;
    }
}

