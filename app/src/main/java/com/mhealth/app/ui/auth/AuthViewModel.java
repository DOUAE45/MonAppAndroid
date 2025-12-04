package com.mhealth.app.ui.auth;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mhealth.app.common.Event;
import com.mhealth.app.data.model.User;
import com.mhealth.app.data.model.UserRole;
import com.mhealth.app.data.repository.AuthRepository;

public class AuthViewModel extends ViewModel {

    private final AuthRepository repository;

    private final MutableLiveData<Boolean> loading = new MutableLiveData<>(false);
    private final MutableLiveData<User> authenticatedUser = new MutableLiveData<>();
    private final MutableLiveData<Event<String>> message = new MutableLiveData<>();

    public AuthViewModel(AuthRepository repository) {
        this.repository = repository;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<User> getAuthenticatedUser() {
        return authenticatedUser;
    }

    public LiveData<Event<String>> getMessage() {
        return message;
    }

    public void login(String email, String password, UserRole role) {
        loading.setValue(true);
        AuthRepository.AuthResult result = repository.login(email, password, role);
        loading.setValue(false);
        message.setValue(new Event<>(result.getMessage()));
        if (result.isSuccess()) {
            authenticatedUser.setValue(result.getUser());
        }
    }

    public static class Factory implements ViewModelProvider.Factory {
        private final AuthRepository repository;

        public Factory(AuthRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(AuthViewModel.class)) {
                return (T) new AuthViewModel(repository);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}

