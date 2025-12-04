package com.mhealth.app;

import android.app.Application;

import com.mhealth.app.common.ServiceLocator;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceLocator.initialize(this);
    }
}

