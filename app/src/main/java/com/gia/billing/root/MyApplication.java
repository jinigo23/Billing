package com.gia.billing.root;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication instantApplication;

    public static MyApplication getApplication() {
        return instantApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instantApplication = this;
    }
}
