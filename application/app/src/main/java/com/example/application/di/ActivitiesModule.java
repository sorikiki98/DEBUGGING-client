package com.example.application.di;

import com.example.application.bug.BugsActivity;
import com.example.application.bug.BugsModule;
import com.example.application.bug.BugsViewModule;
import com.example.application.home.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract HomeActivity contributeHomeActivityInjector();

    @ContributesAndroidInjector(modules = {BugsModule.class, BugsViewModule.class})
    abstract BugsActivity contributeBugsActivityInjector();

}
