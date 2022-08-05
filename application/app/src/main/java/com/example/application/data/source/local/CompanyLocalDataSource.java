package com.example.application.data.source.local;

import android.util.Log;

import com.example.application.data.Company;
import com.example.application.data.source.CompanyDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;

public class CompanyLocalDataSource implements CompanyDataSource {
    private final CompanyDao companyDao;
    private final Scheduler ioScheduler;

    @Inject
    public CompanyLocalDataSource(CompanyDao companyDao, Scheduler scheduler) {
        this.companyDao = companyDao;
        this.ioScheduler = scheduler;
    }

    @Override
    public Flowable<List<Company>> getCompanies() {
        return companyDao.getCompanies()
                .subscribeOn(ioScheduler);
    }

    @Override
    public Completable addCompanyInterest(int companyId) {
        return companyDao.addCompanyInterest(companyId)
                .subscribeOn(ioScheduler)
                .doOnComplete(() -> {
                   Log.d("CompanyLocalDataSource", "complete!");
                });
    }

    @Override
    public Completable removeCompanyInterest(int companyId) {
        return companyDao.removeCompanyInterest(companyId)
                .subscribeOn(ioScheduler)
                .doOnComplete(() -> {
                    Log.d("CompanyLocalDataSource", "complete!");
                });
    }

    @Override
    public void insertCompanies(List<Company> companies) {
        companyDao.insertCompanies(companies);
    }


}
