package com.mhealth.app.ui.dashboard.patient;

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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.transition.MaterialSharedAxis;
import com.mhealth.app.R;
import com.mhealth.app.common.Event;
import com.mhealth.app.common.ServiceLocator;
import com.mhealth.app.data.model.Appointment;
import com.mhealth.app.data.model.Medication;
import com.mhealth.app.data.model.Message;
import com.mhealth.app.data.model.PatientProfile;
import com.mhealth.app.data.model.User;
import com.mhealth.app.databinding.FragmentPatientBinding;

import java.util.ArrayList;
import java.util.List;

public class PatientFragment extends Fragment {

    private static final String ARG_USER = "arg_user";

    public static PatientFragment newInstance(User user) {
        PatientFragment fragment = new PatientFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentPatientBinding binding;
    private PatientViewModel viewModel;
    private User user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = (User) getArguments().getSerializable(ARG_USER);
        }
        MaterialSharedAxis sharedAxis = new MaterialSharedAxis(MaterialSharedAxis.Y, true);
        setEnterTransition(sharedAxis);
        setReturnTransition(new MaterialSharedAxis(MaterialSharedAxis.Y, false));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPatientBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (user == null) {
            return;
        }
        viewModel = new ViewModelProvider(this, new PatientViewModel.Factory(ServiceLocator.getCareRepository(), user))
                .get(PatientViewModel.class);
        setupObservers();
        setupInteractions();
    }

    private void setupObservers() {
        viewModel.getProfile().observe(getViewLifecycleOwner(), this::renderProfile);
        viewModel.getAppointments().observe(getViewLifecycleOwner(), list -> binding.patientAppointments.setText(formatAppointments(list)));
        viewModel.getMedications().observe(getViewLifecycleOwner(), list -> binding.patientMedications.setText(formatMedications(list)));
        viewModel.getMessages().observe(getViewLifecycleOwner(), list -> binding.patientMessages.setText(formatMessages(list)));
        viewModel.getNotifications().observe(getViewLifecycleOwner(), list -> binding.patientNotifications.setText(joinLines(list)));
        viewModel.getActions().observe(getViewLifecycleOwner(), this::displayEvent);
    }

    private void setupInteractions() {
        binding.bookAppointmentButton.setOnClickListener(v -> showAppointmentDialog());
        binding.renewPrescriptionButton.setOnClickListener(v -> showPrescriptionDialog());
        binding.contactDoctorButton.setOnClickListener(v -> showMessageDialog());
    }

    private void renderProfile(@Nullable PatientProfile profile) {
        if (profile == null) {
            binding.patientProfile.setText("Profil introuvable");
            return;
        }
        String content = profile.getFullName() + " • ID " + profile.getMedicalId()
                + "\nAssurance : " + profile.getInsurance()
                + "\nMédecin traitant : " + profile.getPrimaryDoctor();
        binding.patientProfile.setText(content);
    }

    private String formatAppointments(@Nullable List<Appointment> appointments) {
        if (appointments == null || appointments.isEmpty()) {
            return "Aucun rendez-vous programmé";
        }
        List<String> rows = new ArrayList<>();
        for (Appointment appointment : appointments) {
            rows.add(appointment.toDisplayString());
        }
        return TextUtils.join("\n", rows);
    }

    private String formatMedications(@Nullable List<Medication> medications) {
        if (medications == null || medications.isEmpty()) {
            return "Aucun traitement actif";
        }
        List<String> rows = new ArrayList<>();
        for (Medication medication : medications) {
            rows.add(medication.toDisplayString());
        }
        return TextUtils.join("\n", rows);
    }

    private String formatMessages(@Nullable List<Message> messages) {
        if (messages == null || messages.isEmpty()) {
            return "Pas de messages récents";
        }
        List<String> rows = new ArrayList<>();
        for (Message message : messages) {
            rows.add(message.getSubject() + " - " + message.getBody());
        }
        return TextUtils.join("\n", rows);
    }

    private String joinLines(@Nullable List<String> values) {
        if (values == null || values.isEmpty()) {
            return "Aucune notification";
        }
        return TextUtils.join("\n", values);
    }

    private void displayEvent(@Nullable Event<String> event) {
        if (event == null) {
            return;
        }
        String message = event.getContentIfNotHandled();
        if (!TextUtils.isEmpty(message) && getContext() != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    private void showAppointmentDialog() {
        if (getContext() == null) {
            return;
        }
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_patient_appointment, null, false);
        TextInputEditText reasonInput = dialogView.findViewById(R.id.inputAppointmentReason);
        TextInputEditText dateInput = dialogView.findViewById(R.id.inputAppointmentDate);
        TextInputEditText doctorInput = dialogView.findViewById(R.id.inputAppointmentDoctor);

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Planifier un rendez-vous")
                .setView(dialogView)
                .setPositiveButton("Envoyer", (dialog, which) -> {
                    viewModel.requestAppointment(
                            textOf(reasonInput),
                            textOf(dateInput),
                            textOf(doctorInput)
                    );
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void showPrescriptionDialog() {
        if (getContext() == null) {
            return;
        }
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_patient_prescription, null, false);
        TextInputEditText medicationInput = dialogView.findViewById(R.id.inputMedicationName);
        TextInputEditText dosageInput = dialogView.findViewById(R.id.inputMedicationDosage);
        TextInputEditText commentInput = dialogView.findViewById(R.id.inputMedicationComment);

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Renouveler une ordonnance")
                .setView(dialogView)
                .setPositiveButton("Envoyer", (dialog, which) -> {
                    viewModel.renewPrescription(
                            textOf(medicationInput),
                            textOf(dosageInput),
                            textOf(commentInput)
                    );
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void showMessageDialog() {
        if (getContext() == null) {
            return;
        }
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_patient_message, null, false);
        TextInputEditText subjectInput = dialogView.findViewById(R.id.inputMessageSubject);
        TextInputEditText bodyInput = dialogView.findViewById(R.id.inputMessageBody);

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Contacter le médecin")
                .setView(dialogView)
                .setPositiveButton("Envoyer", (dialog, which) -> {
                    viewModel.contactDoctor(
                            textOf(subjectInput),
                            textOf(bodyInput)
                    );
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private String textOf(@Nullable TextInputEditText editText) {
        if (editText == null || editText.getText() == null) {
            return "";
        }
        return editText.getText().toString().trim();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

