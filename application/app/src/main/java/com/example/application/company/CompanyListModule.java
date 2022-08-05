package com.example.application.company;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.CompanyRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CompanyListModule {
    @Binds
    abstract CompanyListContract.View bindCompanyListFragment(CompanyListFragment companyListFragment);

    @Provides
    static CompanyListContract.Presenter provideCompanyListPresenter(CompanyRepository companyRepository, CompanyListContract.View view, SchedulersFacade scheduler) {
        return new CompanyListPresenter(companyRepository, view, scheduler.ui());
    }
}
