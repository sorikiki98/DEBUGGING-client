package com.example.application.company;

import com.example.application.bug.BugListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CompanyFragmentsModule {
    @ContributesAndroidInjector(modules = CompanyListModule.class)
    abstract CompanyListFragment contributeBugsListFragment();
}
