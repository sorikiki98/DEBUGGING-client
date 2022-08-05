package com.example.application.data.source.remote;

import com.example.application.PreferencesManager;
import com.example.application.data.Company;
import com.example.application.data.source.CompanyDataSource;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;

public class CompanyRemoteDataSource implements CompanyDataSource {
    private final CompanyService companyService;
    private final Scheduler ioScheduler;
    private final PreferencesManager preferencesManager;

    @Inject
    public CompanyRemoteDataSource(CompanyService companyService, Scheduler scheduler, PreferencesManager preferencesManager) {
        this.companyService = companyService;
        this.ioScheduler = scheduler;
        this.preferencesManager = preferencesManager;
    }

    @Override
    public Flowable<List<Company>> getCompanies() {
        return companyService.getCompanies(getAuthToken())
                .subscribeOn(ioScheduler);
    }

    @Override
    public Flowable<Optional<Company>> getCompanyWithId(int companyId) {
        return null;
    }

    @Override
    public Completable addCompanyInterest(int companyId) {
        return companyService.addCompanyInterest(companyId, getAuthToken())
                .subscribeOn(ioScheduler);
    }

    @Override
    public Completable removeCompanyInterest(int companyId) {
        return companyService.removeCompanyInterest(companyId, getAuthToken())
                .subscribeOn(ioScheduler);
    }

    @Override
    public void insertCompanies(List<Company> companies) {

    }


    private String getAuthToken() {
        return "Bearer " + preferencesManager.fetchAuthToken();
    }
}
