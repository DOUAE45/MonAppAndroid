package com.mhealth.app.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu; // IMPORTANT
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.mhealth.app.R;
import com.mhealth.app.data.model.User;
import com.mhealth.app.data.model.UserRole;
import com.mhealth.app.databinding.ActivityDashboardBinding;
import com.mhealth.app.ui.auth.LoginActivity;
import com.mhealth.app.ui.dashboard.admin.AdminFragment;
import com.mhealth.app.ui.dashboard.doctor.DoctorFragment;
import com.mhealth.app.ui.dashboard.patient.PatientFragment;
import com.mhealth.app.ui.dashboard.secretary.SecretaryFragment;

public class DashboardActivity extends AppCompatActivity {

    public static final String EXTRA_USER = "DashboardActivity.USER";

    private ActivityDashboardBinding binding;
    private User connectedUser;

    public static Intent createIntent(@NonNull Context context, @NonNull User user) {
        Intent intent = new Intent(context, DashboardActivity.class);
        intent.putExtra(EXTRA_USER, user);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        connectedUser = (User) getIntent().getSerializableExtra(EXTRA_USER);
        if (connectedUser == null) {
            finish();
            return;
        }

        setupToolbar(binding.dashboardToolbar);

        if (savedInstanceState == null) {
            loadFragmentForRole(connectedUser.getRole());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    private void setupToolbar(MaterialToolbar toolbar) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getTitleForRole(connectedUser.getRole()));
        }

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_logout) {
                logout();
                return true;
            }
            return false;
        });
    }

    private void logout() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void loadFragmentForRole(UserRole role) {
        Fragment fragment;

        switch (role) {
            case DOCTOR:
                fragment = DoctorFragment.newInstance(connectedUser);
                break;

            case ADMIN:
                fragment = AdminFragment.newInstance(connectedUser);
                break;

            case SECRETARY:
                fragment = SecretaryFragment.newInstance(connectedUser);
                break;

            case PATIENT:
            default:
                fragment = PatientFragment.newInstance(connectedUser);
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.dashboardContainer, fragment)
                .commit();
    }

    private String getTitleForRole(UserRole role) {
        switch (role) {
            case DOCTOR:
                return getString(R.string.doctor_space);
            case ADMIN:
                return getString(R.string.admin_space);
            case SECRETARY:
                return getString(R.string.secretary_space);
            case PATIENT:
            default:
                return getString(R.string.patient_space);
        }
    }
}
