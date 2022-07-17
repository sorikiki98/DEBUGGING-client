package com.example.application.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.application.DebuggingApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(DebuggingApplication application) {
        return application.getSharedPreferences("authentication", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    PreferencesManager providePreferencesManager(SharedPreferences sharedPreferences) {
        return new PreferencesManager(sharedPreferences);
    }
}
