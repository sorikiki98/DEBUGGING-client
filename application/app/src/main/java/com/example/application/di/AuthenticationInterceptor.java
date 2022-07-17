package com.example.application.di;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class AuthenticationInterceptor implements Interceptor {
    private final PreferencesManager preferencesManager;

    @Inject
    AuthenticationInterceptor(PreferencesManager preferencesManager) {
        this.preferencesManager = preferencesManager;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        String token = preferencesManager.fetchAuthToken();
        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer ".concat(token));
        }
        Log.d("Interceptor", token);
        return chain.proceed(requestBuilder.build());
    }
}

