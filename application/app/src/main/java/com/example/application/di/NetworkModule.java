package com.example.application.di;

import com.example.application.AuthenticationInterceptor;
import com.example.application.PreferencesManager;
import com.example.application.data.remote.api.BugsService;
import com.example.application.data.remote.api.UsersService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    AuthenticationInterceptor provideAuthenticationInterceptor(PreferencesManager preferencesManager) {
        return new AuthenticationInterceptor(preferencesManager);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(AuthenticationInterceptor authenticationInterceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(authenticationInterceptor);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://10.0.2.2:8080/")
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    BugsService provideBugsService(Retrofit retrofit) {
        return retrofit.create(BugsService.class);
    }

    @Provides
    @Singleton
    UsersService provideUsersService(Retrofit retrofit) {
        return retrofit.create(UsersService.class);
    }
}
