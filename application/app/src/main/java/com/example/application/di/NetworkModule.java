package com.example.application.di;

import com.example.application.AuthenticationInterceptor;
import com.example.application.PreferencesManager;
import com.example.application.data.source.remote.BugsService;
import com.example.application.data.source.remote.UsersService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    AuthenticationInterceptor provideAuthenticationInterceptor(PreferencesManager preferencesManager) {
        return new AuthenticationInterceptor(preferencesManager);
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging =  new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(AuthenticationInterceptor authenticationInterceptor, HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(httpLoggingInterceptor);
        client.addInterceptor(authenticationInterceptor);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
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
