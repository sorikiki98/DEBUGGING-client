package com.example.application.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.application.R;
import com.example.application.data.remote.api.BugsService;
import com.example.application.data.remote.api.UsersService;
import com.example.application.PreferencesManager;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class HomeActivity extends AppCompatActivity {
    @Inject
    BugsService bugsApi;

    @Inject
    UsersService usersApi;

    @Inject
    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }
}