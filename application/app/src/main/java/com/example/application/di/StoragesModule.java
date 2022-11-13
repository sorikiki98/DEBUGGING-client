package com.example.application.di;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.example.application.DebuggingApplication;
import com.example.application.PreferencesManager;
import com.example.application.SchedulersFacade;
import com.example.application.data.source.local.AppDatabase;
import com.example.application.data.source.local.BugDao;
import com.example.application.data.source.local.BugLocalDataSource;
import com.example.application.data.source.local.CompanyDao;
import com.example.application.data.source.local.CompanyLocalDataSource;
import com.example.application.data.source.local.ProductDao;
import com.example.application.data.source.local.ProductLocalDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StoragesModule {
    @Singleton
    @Provides
    Context provideContext(DebuggingApplication application) {
        return application;
    }

    @Singleton
    @Provides
    SharedPreferences provideSharedPreferences(DebuggingApplication application) {
        return application.getSharedPreferences("authentication", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    AppDatabase provideAppDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "debugging").build();
    }

    @Singleton
    @Provides
    BugDao provideBugsDao(AppDatabase appDatabase) {
        return appDatabase.bugsDao();
    }

    @Singleton
    @Provides
    CompanyDao provideCompanyDao(AppDatabase appDatabase) { return appDatabase.companyDao(); }

    @Singleton
    @Provides
    ProductDao provideProductDao(AppDatabase appDatabase) { return appDatabase.productDao(); }
}
