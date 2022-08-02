package com.example.application.home;

import com.example.application.bug.BugsListFragment;
import com.example.application.bug.BugsListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeFragmentsModule {
    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();
}
