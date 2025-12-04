package com.mhealth.app.common;

import android.app.Application;

import com.mhealth.app.data.local.DatabaseSeeder;
import com.mhealth.app.data.local.MHealthDatabase;
import com.mhealth.app.data.repository.AuthRepository;
import com.mhealth.app.data.repository.CareRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ServiceLocator {

    private static Application application;
    private static AuthRepository authRepository;
    private static CareRepository careRepository;
    private static MHealthDatabase database;
    private static final ExecutorService IO_EXECUTOR = Executors.newSingleThreadExecutor();

    private ServiceLocator() {
    }

    public static void initialize(Application app) {
        application = app;
        authRepository = new AuthRepository();
        database = MHealthDatabase.build(app);
        IO_EXECUTOR.execute(() -> DatabaseSeeder.seed(database.careDao()));
        careRepository = new CareRepository(database.careDao(), IO_EXECUTOR);
    }

    public static Application getApplication() {
        return application;
    }

    public static AuthRepository getAuthRepository() {
        if (authRepository == null) {
            authRepository = new AuthRepository();
        }
        return authRepository;
    }

    public static CareRepository getCareRepository() {
        if (careRepository == null) {
            if (database == null && application != null) {
                database = MHealthDatabase.build(application);
                IO_EXECUTOR.execute(() -> DatabaseSeeder.seed(database.careDao()));
            }
            careRepository = new CareRepository(database.careDao(), IO_EXECUTOR);
        }
        return careRepository;
    }
}

