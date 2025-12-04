package com.mhealth.app.ui.dashboard.doctor;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.MutableLiveData;


import com.mhealth.app.common.Event;
import com.mhealth.app.data.model.Appointment;
import com.mhealth.app.data.model.MedicalResult;
import com.mhealth.app.data.model.Message;
import com.mhealth.app.data.model.PatientProfile;
import com.mhealth.app.data.model.User;
import com.mhealth.app.data.repository.CareRepository;

import java.util.List;

public class DoctorViewModel extends ViewModel {

    private final CareRepository repository;
    private final User doctor;

    private final LiveData<List<PatientProfile>> patients;
    private final LiveData<List<Appointment>> appointments;
    private final LiveData<List<MedicalResult>> results;
    private final LiveData<List<Message>> messages;
    private final MutableLiveData<Event<String>> actions = new MutableLiveData<>();

    public DoctorViewModel(CareRepository repository, User doctor) {
        this.repository = repository;
        this.doctor = doctor;
        this.patients = repository.getPatientsForDoctor(doctor.getId());
        this.appointments = repository.getAppointmentsForDoctor(doctor.getId());
        this.results = repository.getMedicalResults(doctor.getId());
        this.messages = repository.getDoctorInbox(doctor.getId());
    }

    public LiveData<List<PatientProfile>> getPatients() {
        return patients;
    }

    public LiveData<List<Appointment>> getAppointments() {
        return appointments;
    }

    public LiveData<List<MedicalResult>> getResults() {
        return results;
    }

    public LiveData<List<Message>> getMessages() {
        return messages;
    }

    public LiveData<Event<String>> getActions() {
        return actions;
    }

    public void updateAvailability() {
        actions.setValue(new Event<>(repository.updateDoctorAvailability(doctor.getId())));
    }

    public void addResultComment() {
        actions.setValue(new Event<>(repository.commentMedicalResult(doctor.getId())));
    }

    public void replyToPatient() {
        actions.setValue(new Event<>(repository.replyToPatient(doctor.getId())));
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final CareRepository repository;
        private final User doctor;

        public Factory(CareRepository repository, User doctor) {
            this.repository = repository;
            this.doctor = doctor;
        }

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(DoctorViewModel.class)) {
                return (T) new DoctorViewModel(repository, doctor);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}

