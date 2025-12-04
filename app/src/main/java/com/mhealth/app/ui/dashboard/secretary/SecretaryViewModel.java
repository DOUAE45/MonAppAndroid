package com.mhealth.app.ui.dashboard.secretary;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.MutableLiveData;


import com.mhealth.app.common.Event;
import com.mhealth.app.data.model.Appointment;
import com.mhealth.app.data.model.User;
import com.mhealth.app.data.repository.CareRepository;

import java.util.List;

public class SecretaryViewModel extends ViewModel {

    private final CareRepository repository;
    private final User secretary;

    private final LiveData<List<Appointment>> appointments;
    private final LiveData<List<String>> patients;
    private final LiveData<List<String>> escalations;
    private final MutableLiveData<Event<String>> actions = new MutableLiveData<>();

    public SecretaryViewModel(CareRepository repository, User secretary) {
        this.repository = repository;
        this.secretary = secretary;
        this.appointments = repository.getSecretaryAppointments(secretary.getId());
        this.patients = repository.getSecretaryPatients(secretary.getId());
        this.escalations = repository.getSecretaryEscalations(secretary.getId());
    }

    public LiveData<List<Appointment>> getAppointments() {
        return appointments;
    }

    public LiveData<List<String>> getPatients() {
        return patients;
    }

    public LiveData<List<String>> getEscalations() {
        return escalations;
    }

    public LiveData<Event<String>> getActions() {
        return actions;
    }

    public void confirmAppointment() {
        actions.setValue(new Event<>(repository.confirmAppointment(secretary.getId())));
    }

    public void updatePatientFile() {
        actions.setValue(new Event<>(repository.updatePatientFile(secretary.getId())));
    }

    public void escalateMessage() {
        actions.setValue(new Event<>(repository.escalateMessage(secretary.getId())));
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final CareRepository repository;
        private final User secretary;

        public Factory(CareRepository repository, User secretary) {
            this.repository = repository;
            this.secretary = secretary;
        }

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(SecretaryViewModel.class)) {
                return (T) new SecretaryViewModel(repository, secretary);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}

