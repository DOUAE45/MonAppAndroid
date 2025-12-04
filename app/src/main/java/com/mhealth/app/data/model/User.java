package com.mhealth.app.data.model;

import java.io.Serializable;

public class User implements Serializable {
    private final String id;
    private final String displayName;
    private final String email;
    private final UserRole role;

    public User(String id, String displayName, String email, UserRole role) {
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }
}

