package com.mhealth.app.ui.dashboard.patient;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mhealth.app.common.Event;
import com.mhealth.app.data.model.Appointment;
import com.mhealth.app.data.model.Medication;
import com.mhealth.app.data.model.Message;
import com.mhealth.app.data.model.PatientProfile;
import com.mhealth.app.data.model.User;
import com.mhealth.app.data.repository.CareRepository;

import java.util.List;

public class PatientViewModel extends ViewModel {

    private final CareRepository repository;
    private final User patient;

    private final LiveData<PatientProfile> profile;
    private final LiveData<List<Appointment>> appointments;
    private final LiveData<List<Medication>> medications;
    private final LiveData<List<Message>> messages;
    private final LiveData<List<String>> notifications;
    private final MutableLiveData<Event<String>> actions = new MutableLiveData<>();

    public PatientViewModel(CareRepository repository, User patient) {
        this.repository = repository;
        this.patient = patient;
        this.profile = repository.getPatientProfile(patient.getId());
        this.appointments = repository.getAppointmentsForPatient(patient.getId());
        this.medications = repository.getMedicationsForPatient(patient.getId());
        this.messages = repository.getMessagesForUser(patient.getId());
        this.notifications = repository.getNotificationsForPatient(patient.getId());
    }

    public LiveData<PatientProfile> getProfile() {
        return profile;
    }

    public LiveData<List<Appointment>> getAppointments() {
        return appointments;
    }

    public LiveData<List<Medication>> getMedications() {
        return medications;
    }

    public LiveData<List<Message>> getMessages() {
        return messages;
    }

    public LiveData<List<String>> getNotifications() {
        return notifications;
    }

    public LiveData<Event<String>> getActions() {
        return actions;
    }

    public void requestAppointment(String reason, String preferredDate, String preferredDoctor) {
        actions.setValue(new Event<>(repository.requestAppointment(patient.getId(), reason, preferredDate, preferredDoctor)));
    }

    public void renewPrescription(String medication, String dosage, String comment) {
        actions.setValue(new Event<>(repository.renewPrescription(patient.getId(), medication, dosage, comment)));
    }

    public void contactDoctor(String subject, String message) {
        actions.setValue(new Event<>(repository.sendMessageToDoctor(patient.getId(), subject, message)));
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final CareRepository repository;
        private final User patient;

        public Factory(CareRepository repository, User patient) {
            this.repository = repository;
            this.patient = patient;
        }

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(PatientViewModel.class)) {
                return (T) new PatientViewModel(repository, patient);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}

