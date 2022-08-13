package com.example.application.company;

import com.example.application.data.source.repository.CompanyRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CompanySearchModule {
    @Binds
    abstract CompanySearchContract.View bindCompanySearchFragment(CompanySearchFragment companySearchFragment);

    @Provides
    static CompanySearchContract.Presenter provideCompanySearchPresenter(CompanyRepository companyRepository, CompanySearchContract.View view) {
        return new CompanySearchPresenter(companyRepository, view);
    }
}
