package com.example.application.register;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RegisterFragmentsModule {
    @ContributesAndroidInjector(modules = RegisterModule.class)
    abstract RegisterFragment contributeRegisterFragment();

    @ContributesAndroidInjector
    abstract RegisterCompletedFragment contributeRegisterCompletedFragment();
}
