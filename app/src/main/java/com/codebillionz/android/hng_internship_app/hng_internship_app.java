package com.codebillionz.android.hng_internship_app;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class hng_internship_app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
