package com.mhealth.app.ui.dashboard.admin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.transition.MaterialSharedAxis;
import com.mhealth.app.R;
import com.mhealth.app.common.Event;
import com.mhealth.app.common.ServiceLocator;
import com.mhealth.app.data.model.User;
import com.mhealth.app.data.model.UserRole;
import com.mhealth.app.databinding.FragmentAdminBinding;

import java.util.ArrayList;
import java.util.List;

public class AdminFragment extends Fragment {

    private static final String ARG_USER = "arg_user";

    public static AdminFragment newInstance(User user) {
        AdminFragment fragment = new AdminFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentAdminBinding binding;
    private AdminViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MaterialSharedAxis axis = new MaterialSharedAxis(MaterialSharedAxis.X, true);
        setEnterTransition(axis);
        setReturnTransition(new MaterialSharedAxis(MaterialSharedAxis.X, false));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, new AdminViewModel.Factory(ServiceLocator.getCareRepository()))
                .get(AdminViewModel.class);
        setupObservers();
        setupInteractions();
    }

    private void setupObservers() {
        viewModel.getUsers().observe(getViewLifecycleOwner(), list -> binding.adminUsers.setText(formatUsers(list)));
        viewModel.getAlerts().observe(getViewLifecycleOwner(), list -> binding.adminAlerts.setText(joinList(list)));
        viewModel.getAuditEntries().observe(getViewLifecycleOwner(), list -> binding.adminAudit.setText(joinList(list)));
        viewModel.getActions().observe(getViewLifecycleOwner(), this::displayEvent);
    }

    private void setupInteractions() {
        binding.manageUsersButton.setOnClickListener(v -> showCreateUserDialog());
        binding.refreshAlertsButton.setOnClickListener(v -> showAlertDialog());
        binding.runAuditButton.setOnClickListener(v -> showAuditDialog());
    }

    private String formatUsers(@Nullable List<User> users) {
        if (users == null || users.isEmpty()) {
            return "Aucun compte";
        }
        List<String> rows = new ArrayList<>();
        for (User user : users) {
            rows.add(user.getDisplayName() + " • " + user.getRole().name());
        }
        return TextUtils.join("\n", rows);
    }

    private String joinList(@Nullable List<String> items) {
        if (items == null || items.isEmpty()) {
            return "Aucun enregistrement";
        }
        return TextUtils.join("\n", items);
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

    private void showCreateUserDialog() {
        if (getContext() == null) {
            return;
        }
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_admin_user, null, false);
        TextInputEditText nameInput = dialogView.findViewById(R.id.inputAdminUserName);
        TextInputEditText emailInput = dialogView.findViewById(R.id.inputAdminUserEmail);
        MaterialAutoCompleteTextView roleInput = dialogView.findViewById(R.id.inputAdminUserRole);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.role_entries,
                android.R.layout.simple_list_item_1
        );
        roleInput.setAdapter(adapter);

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Créer un compte utilisateur")
                .setView(dialogView)
                .setPositiveButton("Enregistrer", (dialog, which) -> {
                    viewModel.createUser(
                            safeText(nameInput),
                            safeText(emailInput),
                            UserRole.fromDisplayLabel(safeText(roleInput))
                    );
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void showAlertDialog() {
        if (getContext() == null) {
            return;
        }
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_admin_alert, null, false);
        TextInputEditText alertInput = dialogView.findViewById(R.id.inputAdminAlert);

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Ajouter une alerte système")
                .setView(dialogView)
                .setPositiveButton("Ajouter", (dialog, which) -> viewModel.addAlert(safeText(alertInput)))
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void showAuditDialog() {
        if (getContext() == null) {
            return;
        }
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_admin_audit, null, false);
        TextInputEditText auditInput = dialogView.findViewById(R.id.inputAdminAudit);

        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Enregistrer un audit")
                .setView(dialogView)
                .setPositiveButton("Enregistrer", (dialog, which) -> viewModel.logAudit(safeText(auditInput)))
                .setNegativeButton("Annuler", null)
                .show();
    }

    private String safeText(@Nullable TextInputEditText editText) {
        if (editText == null || editText.getText() == null) {
            return "";
        }
        return editText.getText().toString().trim();
    }

    private String safeText(@Nullable MaterialAutoCompleteTextView view) {
        if (view == null || view.getText() == null) {
            return "";
        }
        return view.getText().toString().trim();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

