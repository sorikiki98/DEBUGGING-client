package com.example.application.di;

import com.example.application.bug.BugActivity;
import com.example.application.bug.BugFragmentsModule;
import com.example.application.company.CompanyActivity;
import com.example.application.company.CompanyFragmentsModule;
import com.example.application.home.HomeActivity;
import com.example.application.home.HomeModule;
import com.example.application.login.LoginActivity;
import com.example.application.home.HomeFragmentsModule;
import com.example.application.login.LoginModule;
import com.example.application.product.ProductActivity;
import com.example.application.product.ProductFragmentsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivitiesModule {
    @ContributesAndroidInjector(modules = {HomeFragmentsModule.class, HomeModule.class})
    abstract HomeActivity contributeHomeActivityInjector();

    @ContributesAndroidInjector(modules = {BugFragmentsModule.class})
    abstract BugActivity contributeBugsActivityInjector();

    @ContributesAndroidInjector(modules = {LoginModule.class})
    abstract LoginActivity contributeLoginActivityInjector();

    @ContributesAndroidInjector(modules = {CompanyFragmentsModule.class})
    abstract CompanyActivity contributeCompanyActivityInjector();

    @ContributesAndroidInjector(modules = {ProductFragmentsModule.class})
    abstract ProductActivity contributeProductActivityInjector();
}
