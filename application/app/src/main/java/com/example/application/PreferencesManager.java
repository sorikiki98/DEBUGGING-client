package com.example.application;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesManager {
    private final SharedPreferences sharedPreferences;

    @Inject
    public PreferencesManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void saveAuthToken(String token) {
        sharedPreferences.edit().putString("token", token).apply();
    }

    public void saveUserName(String userName) {
        sharedPreferences.edit().putString("user_name", userName).apply();
    }

    public String fetchAuthToken() {
        return sharedPreferences.getString("token", null);
    }

    public String fetchUserName() {
        return sharedPreferences.getString("user_name", null);
    }

    public void clearAuthToken() {
        sharedPreferences.edit().clear().apply();
    }
}

