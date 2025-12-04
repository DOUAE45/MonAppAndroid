package com.mhealth.app.ui.dashboard.doctor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mhealth.app.common.Event;
import com.mhealth.app.common.ServiceLocator;
import com.mhealth.app.data.model.Appointment;
import com.mhealth.app.data.model.MedicalResult;
import com.google.android.material.transition.MaterialSharedAxis;
import com.mhealth.app.data.model.Message;
import com.mhealth.app.data.model.PatientProfile;
import com.mhealth.app.data.model.User;
import com.mhealth.app.databinding.FragmentDoctorBinding;

import java.util.ArrayList;
import java.util.List;

public class DoctorFragment extends Fragment {

    private static final String ARG_USER = "arg_user";

    public static DoctorFragment newInstance(User user) {
        DoctorFragment fragment = new DoctorFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentDoctorBinding binding;
    private DoctorViewModel viewModel;
    private User doctor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            doctor = (User) getArguments().getSerializable(ARG_USER);
        }
        MaterialSharedAxis axis = new MaterialSharedAxis(MaterialSharedAxis.Z, true);
        setEnterTransition(axis);
        setReturnTransition(new MaterialSharedAxis(MaterialSharedAxis.Z, false));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDoctorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (doctor == null) {
            return;
        }
        viewModel = new ViewModelProvider(this, new DoctorViewModel.Factory(ServiceLocator.getCareRepository(), doctor))
                .get(DoctorViewModel.class);
        setupObservers();
        setupInteractions();
    }

    private void setupObservers() {
        viewModel.getPatients().observe(getViewLifecycleOwner(), list -> binding.doctorPatients.setText(formatPatients(list)));
        viewModel.getAppointments().observe(getViewLifecycleOwner(), list -> binding.doctorAppointments.setText(formatAppointments(list)));
        viewModel.getResults().observe(getViewLifecycleOwner(), list -> binding.doctorResults.setText(formatResults(list)));
        viewModel.getMessages().observe(getViewLifecycleOwner(), list -> binding.doctorMessages.setText(formatMessages(list)));
        viewModel.getActions().observe(getViewLifecycleOwner(), this::displayAction);
    }

    private void setupInteractions() {
        binding.updateAvailabilityButton.setOnClickListener(v -> viewModel.updateAvailability());
        binding.addCommentButton.setOnClickListener(v -> viewModel.addResultComment());
        binding.replyPatientButton.setOnClickListener(v -> viewModel.replyToPatient());
    }

    private String formatPatients(@Nullable List<PatientProfile> patients) {
        if (patients == null || patients.isEmpty()) {
            return "Aucun patient assigné";
        }
        List<String> rows = new ArrayList<>();
        for (PatientProfile profile : patients) {
            rows.add(profile.getFullName() + " • " + profile.getMedicalId());
        }
        return TextUtils.join("\n", rows);
    }

    private String formatAppointments(@Nullable List<Appointment> appointments) {
        if (appointments == null || appointments.isEmpty()) {
            return "Planning vide";
        }
        List<String> rows = new ArrayList<>();
        for (Appointment appointment : appointments) {
            rows.add(appointment.getDate() + " - " + appointment.getTime() + " - " + appointment.getPatientName());
        }
        return TextUtils.join("\n", rows);
    }

    private String formatResults(@Nullable List<MedicalResult> results) {
        if (results == null || results.isEmpty()) {
            return "Aucun résultat en attente";
        }
        List<String> rows = new ArrayList<>();
        for (MedicalResult result : results) {
            rows.add(result.toDisplayString());
        }
        return TextUtils.join("\n", rows);
    }

    private String formatMessages(@Nullable List<Message> messages) {
        if (messages == null || messages.isEmpty()) {
            return "Boîte de réception vide";
        }
        List<String> rows = new ArrayList<>();
        for (Message message : messages) {
            rows.add(message.getFrom() + " : " + message.getSubject());
        }
        return TextUtils.join("\n", rows);
    }

    private void displayAction(@Nullable Event<String> event) {
        if (event == null || getContext() == null) {
            return;
        }
        String message = event.getContentIfNotHandled();
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

