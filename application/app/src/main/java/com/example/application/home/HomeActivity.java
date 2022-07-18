package com.example.application.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.application.R;
import com.example.application.data.api.BugsService;
import com.example.application.data.api.UsersService;
import com.example.application.data.request.UserLogIn;
import com.example.application.data.response.Bug;
import com.example.application.data.response.UserAuthentication;
import com.example.application.di.PreferencesManager;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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