package com.mhealth.app.ui.dashboard.admin;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.MutableLiveData;


import com.mhealth.app.common.Event;
import com.mhealth.app.data.model.User;
import com.mhealth.app.data.repository.CareRepository;

import java.util.List;

public class AdminViewModel extends ViewModel {

    private final CareRepository repository;
    private final LiveData<List<User>> users;
    private final LiveData<List<String>> alerts;
    private final LiveData<List<String>> auditEntries;
    private final MutableLiveData<Event<String>> actions = new MutableLiveData<>();

    public AdminViewModel(CareRepository repository) {
        this.repository = repository;
        this.users = repository.getAllUsers();
        this.alerts = repository.getSystemAlerts();
        this.auditEntries = repository.getAuditLog();
    }

    public LiveData<List<User>> getUsers() {
        return users;
    }

    public LiveData<List<String>> getAlerts() {
        return alerts;
    }

    public LiveData<List<String>> getAuditEntries() {
        return auditEntries;
    }

    public LiveData<Event<String>> getActions() {
        return actions;
    }

    public void createUser(String name, String email, com.mhealth.app.data.model.UserRole role) {
        actions.setValue(new Event<>(repository.createOrUpdateUser(name, email, role)));
    }

    public void addAlert(String alert) {
        actions.setValue(new Event<>(repository.addSystemAlert(alert)));
    }

    public void logAudit(String entry) {
        actions.setValue(new Event<>(repository.logAuditEntry(entry)));
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final CareRepository repository;

        public Factory(CareRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(AdminViewModel.class)) {
                return (T) new AdminViewModel(repository);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}

