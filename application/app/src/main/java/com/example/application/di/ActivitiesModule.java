package com.example.application.di;

import com.example.application.bug.BugsActivity;
import com.example.application.bug.BugsDataModule;
import com.example.application.bug.BugsFragmentsModule;
import com.example.application.home.HomeActivity;
import com.example.application.home.HomeMenuModule;
import com.example.application.login.LoginActivity;
import com.example.application.home.HomeFragmentsModule;
import com.example.application.login.LoginModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivitiesModule {
    @ContributesAndroidInjector(modules = {HomeFragmentsModule.class, HomeMenuModule.class})
    abstract HomeActivity contributeHomeActivityInjector();

    @ContributesAndroidInjector(modules = {BugsDataModule.class, BugsFragmentsModule.class})
    abstract BugsActivity contributeBugsActivityInjector();

    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity contributeLoginActivityInjector();
}
