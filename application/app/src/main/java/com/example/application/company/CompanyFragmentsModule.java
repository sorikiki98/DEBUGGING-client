package com.example.application.company;

import com.example.application.bug.BugListModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CompanyFragmentsModule {
    @ContributesAndroidInjector(modules = CompanyListModule.class)
    abstract CompanyListFragment contributeCompanyListFragment();

    @ContributesAndroidInjector(modules = CompanyItemModule.class)
    abstract CompanyItemFragment contributeCompanyItemFragment();

    @ContributesAndroidInjector(modules = CompanyReservationModule.class)
    abstract CompanyReservationFragment contributeCompanyReservationFragment();

    @ContributesAndroidInjector(modules = CompanyReservationCheckModule.class)
    abstract CompanyReservationCheckFragment contributeCompanyReservationCheckFragment();

    @ContributesAndroidInjector(modules = CompanySearchModule.class)
    abstract CompanySearchFragment contributeCompanySearchFragment();
}
