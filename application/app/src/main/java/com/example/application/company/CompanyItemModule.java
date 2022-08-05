package com.example.application.company;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.CompanyRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CompanyItemModule {
    @Binds
    abstract CompanyItemContract.View bindCompanyItemFragment(CompanyItemFragment companyItemFragment);

    @Provides
    static CompanyItemContract.Presenter provideCompanyItemPresenter(CompanyRepository companyRepository, CompanyItemContract.View view, SchedulersFacade scheduler) {
        return new CompanyItemPresenter(companyRepository, view, scheduler.ui());
    }
}
