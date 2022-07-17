package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_main);

        usersApi.logIn(new UserLogIn("김예솔", "abc1234")).enqueue(new Callback<UserAuthentication>() {
            @Override
            public void onResponse(Call<UserAuthentication> call, Response<UserAuthentication> response) {
                if (response.isSuccessful()) {
                    preferencesManager.saveAuthToken(response.body().getToken());
                    bugsApi.getBugs().enqueue(new Callback<List<Bug>>() {
                        @Override
                        public void onResponse(Call<List<Bug>> call, Response<List<Bug>> response) {
                            if (response.isSuccessful()) {
                                Log.d("MainActivity", response.body().toString());
                                ((TextView) findViewById(R.id.testTextView)).setText(response.body().toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Bug>> call, Throwable t) {
                            Log.d("MainActivity", t.toString());
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<UserAuthentication> call, Throwable t) {

            }
        });
    }
}