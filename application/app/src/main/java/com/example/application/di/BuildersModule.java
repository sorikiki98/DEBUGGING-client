package com.example.application.di;

import com.example.application.home.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract HomeActivity contributeHomeActivityInjector();
}
