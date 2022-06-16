package com.example.android_chat.activities;

import android.app.Application;
import android.content.Context;

public class android_chat extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
