package com.mhealth.app.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mhealth.app.data.model.User;
import com.mhealth.app.data.model.UserRole;

@Entity(tableName = "users")
public class UserEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private final String id;

    @ColumnInfo(name = "display_name")
    private final String displayName;

    @ColumnInfo(name = "email")
    private final String email;

    @ColumnInfo(name = "role")
    private final String role;

    public UserEntity(
            @NonNull String id,
            String displayName,
            String email,
            String role
    ) {
        this.id = id;
        this.displayName = displayName;
        this.email = email;
        this.role = role;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public User toModel() {
        return new User(id, displayName, email, UserRole.valueOf(role));
    }
}

