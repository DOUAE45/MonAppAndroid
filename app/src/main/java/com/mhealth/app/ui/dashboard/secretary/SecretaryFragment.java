package com.mhealth.app.ui.dashboard.secretary;

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
import com.mhealth.app.data.model.User;
import com.mhealth.app.databinding.FragmentSecretaryBinding;
import com.google.android.material.transition.MaterialSharedAxis;

import java.util.ArrayList;
import java.util.List;

public class SecretaryFragment extends Fragment {

    private static final String ARG_USER = "arg_user";

    public static SecretaryFragment newInstance(User user) {
        SecretaryFragment fragment = new SecretaryFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentSecretaryBinding binding;
    private SecretaryViewModel viewModel;
    private User secretary;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            secretary = (User) getArguments().getSerializable(ARG_USER);
        }
        MaterialSharedAxis axis = new MaterialSharedAxis(MaterialSharedAxis.Y, true);
        setEnterTransition(axis);
        setReturnTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, false));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSecretaryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (secretary == null) {
            return;
        }
        viewModel = new ViewModelProvider(this, new SecretaryViewModel.Factory(ServiceLocator.getCareRepository(), secretary))
                .get(SecretaryViewModel.class);
        setupObservers();
        setupInteractions();
    }

    private void setupObservers() {
        viewModel.getAppointments().observe(getViewLifecycleOwner(), list -> binding.secretaryAppointments.setText(formatAppointments(list)));
        viewModel.getPatients().observe(getViewLifecycleOwner(), list -> binding.secretaryPatients.setText(joinLines(list)));
        viewModel.getEscalations().observe(getViewLifecycleOwner(), list -> binding.secretaryEscalations.setText(joinLines(list)));
        viewModel.getActions().observe(getViewLifecycleOwner(), this::displayEvent);
    }

    private void setupInteractions() {
        binding.confirmAppointmentButton.setOnClickListener(v -> viewModel.confirmAppointment());
        binding.updatePatientFileButton.setOnClickListener(v -> viewModel.updatePatientFile());
        binding.escalateMessageButton.setOnClickListener(v -> viewModel.escalateMessage());
    }

    private String formatAppointments(@Nullable List<Appointment> appointments) {
        if (appointments == null || appointments.isEmpty()) {
            return "Aucun rendez-vous à traiter";
        }
        List<String> rows = new ArrayList<>();
        for (Appointment appointment : appointments) {
            rows.add(appointment.getDate() + " " + appointment.getTime() + " • " + appointment.getPatientName());
        }
        return TextUtils.join("\n", rows);
    }

    private String joinLines(@Nullable List<String> values) {
        if (values == null || values.isEmpty()) {
            return "Aucun élément";
        }
        return TextUtils.join("\n", values);
    }

    private void displayEvent(@Nullable Event<String> event) {
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

