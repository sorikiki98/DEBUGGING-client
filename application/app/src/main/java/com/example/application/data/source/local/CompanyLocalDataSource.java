package com.example.application.data.source.local;

import android.util.Log;

import com.example.application.data.Company;
import com.example.application.data.Reservation;
import com.example.application.data.ReservationForm;
import com.example.application.data.source.CompanyDataSource;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Scheduler;

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
    public Flowable<Optional<Company>> getCompanyWithId(int companyId) {
        return companyDao.getCompany(companyId)
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
    public Maybe<Integer> reserveCompany(int companyId, ReservationForm reservationForm) {
        return null;
    }

    @Override
    public Maybe<Reservation> getReservationInformation(int reservationId) {
        return null;
    }

    @Override
    public void insertCompanies(List<Company> companies) {
        companyDao.insertCompanies(companies);
    }


}
