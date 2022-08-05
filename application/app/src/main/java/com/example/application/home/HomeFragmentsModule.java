package com.example.application.home;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeFragmentsModule {
    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();
}
