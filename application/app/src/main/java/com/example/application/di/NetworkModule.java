package com.example.application.di;

import com.example.application.AuthenticationInterceptor;
import com.example.application.PreferencesManager;
import com.example.application.data.source.remote.BugService;
import com.example.application.data.source.remote.CompanyService;
import com.example.application.data.source.remote.ProductService;
import com.example.application.data.source.remote.UserService;
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
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
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
    BugService provideBugsService(Retrofit retrofit) {
        return retrofit.create(BugService.class);
    }

    @Provides
    @Singleton
    UserService provideUsersService(Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }

    @Provides
    @Singleton
    CompanyService provideCompanyService(Retrofit retrofit) {
        return retrofit.create(CompanyService.class);
    }

    @Provides
    @Singleton
    ProductService provideProductService(Retrofit retrofit) {
        return retrofit.create(ProductService.class);
    }
}
