package com.mhealth.app.ui.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.mhealth.app.R;
import com.mhealth.app.common.Event;
import com.mhealth.app.common.ServiceLocator;
import com.mhealth.app.data.model.User;
import com.mhealth.app.data.model.UserRole;
import com.mhealth.app.databinding.ActivityLoginBinding;
import com.mhealth.app.ui.dashboard.DashboardActivity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private AuthViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this, new AuthViewModel.Factory(ServiceLocator.getAuthRepository()))
                .get(AuthViewModel.class);

        setupRoleDropdown();
        setupObservers();

        binding.loginButton.setOnClickListener(v -> attemptLogin());
    }

    private void setupRoleDropdown() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.role_entries,
                android.R.layout.simple_list_item_1
        );
        binding.roleInput.setAdapter(adapter);
    }

    private void setupObservers() {
        viewModel.getLoading().observe(this, loading -> binding.loginProgress.setVisibility(loading ? View.VISIBLE : View.GONE));

        viewModel.getMessage().observe(this, event -> {
            String content = event != null ? event.getContentIfNotHandled() : null;
            if (!TextUtils.isEmpty(content)) {
                Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getAuthenticatedUser().observe(this, this::openDashboard);
    }

    private void attemptLogin() {
        String email = getTextFromField(binding.emailInput.getText());
        String password = getTextFromField(binding.passwordInput.getText());
        String roleLabel = getTextFromField(binding.roleInput.getText());

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(roleLabel)) {
            Toast.makeText(this, "Complétez tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        UserRole role = UserRole.fromDisplayLabel(roleLabel);
        viewModel.login(email, password, role);
    }

    private void openDashboard(User user) {
        if (user == null) {
            return;
        }
        startActivity(DashboardActivity.createIntent(this, user));
        finish();
    }

    private String getTextFromField(@Nullable CharSequence text) {
        if (text == null) {
            return "";
        }
        return text.toString().trim();
    }
}

