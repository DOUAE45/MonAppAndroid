package com.mhealth.app.data.repository;

import androidx.annotation.Nullable;

import com.mhealth.app.data.model.User;
import com.mhealth.app.data.model.UserRole;

import java.util.ArrayList;
import java.util.List;

public class AuthRepository {

    private final List<UserRecord> users = new ArrayList<>();

    public AuthRepository() {
        users.add(new UserRecord(new User("patient-1", "Sara Benali", "patient@mhealth.com", UserRole.PATIENT), "patient123"));
        users.add(new UserRecord(new User("doctor-1", "Dr. Idris Khellaf", "doctor@mhealth.com", UserRole.DOCTOR), "doctor123"));
        users.add(new UserRecord(new User("admin-1", "Lamia Rached", "admin@mhealth.com", UserRole.ADMIN), "admin123"));
        users.add(new UserRecord(new User("secretary-1", "Nora Bouzid", "secretary@mhealth.com", UserRole.SECRETARY), "secretary123"));
    }

    public AuthResult login(String email, String password, UserRole role) {
        for (UserRecord record : users) {
            if (record.user.getEmail().equalsIgnoreCase(email)
                    && record.password.equals(password)
                    && record.user.getRole() == role) {
                return new AuthResult(true, "Connexion réussie", record.user);
            }
        }
        return new AuthResult(false, "Identifiants invalides", null);
    }

    private static class UserRecord {
        private final User user;
        private final String password;

        private UserRecord(User user, String password) {
            this.user = user;
            this.password = password;
        }
    }

    public static class AuthResult {
        private final boolean success;
        private final String message;
        private final User user;

        public AuthResult(boolean success, String message, @Nullable User user) {
            this.success = success;
            this.message = message;
            this.user = user;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        @Nullable
        public User getUser() {
            return user;
        }
    }
}

