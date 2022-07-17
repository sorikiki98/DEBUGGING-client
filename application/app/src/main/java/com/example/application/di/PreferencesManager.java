package com.example.application.di;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesManager {
    private final SharedPreferences sharedPreferences;

    @Inject
    PreferencesManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveAuthToken(String token) {
        sharedPreferences.edit().putString("token", token).apply();
    }

    public String fetchAuthToken() {
        return sharedPreferences.getString("token", null);
    }
}

