package com.example.application.data.source.remote;

import com.example.application.PreferencesManager;
import com.example.application.SchedulersFacade;
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

public class CompanyRemoteDataSource implements CompanyDataSource {
    private final CompanyService companyService;
    private final Scheduler ioScheduler;

    @Inject
    public CompanyRemoteDataSource(CompanyService companyService, SchedulersFacade schedulersFacade) {
        this.companyService = companyService;
        this.ioScheduler = schedulersFacade.io();
    }

    @Override
    public Flowable<List<Company>> getCompanies() {
        return companyService.getCompanies()
                .subscribeOn(ioScheduler);
    }

    @Override
    public Flowable<Optional<Company>> getCompanyWithId(int companyId) {
        return null;
    }

    @Override
    public Completable addCompanyInterest(int companyId) {
        return companyService.addCompanyInterest(companyId)
                .subscribeOn(ioScheduler);
    }

    @Override
    public Completable removeCompanyInterest(int companyId) {
        return companyService.removeCompanyInterest(companyId)
                .subscribeOn(ioScheduler);
    }

    @Override
    public Maybe<Integer> reserveCompany(int companyId, ReservationForm reservationForm) {
        return companyService.reserveCompany(companyId, reservationForm)
                .subscribeOn(ioScheduler);
    }

    @Override
    public Maybe<Reservation> getReservationInformation(int reservationId) {
        return companyService.getReservationInformation(reservationId)
                .subscribeOn(ioScheduler);
    }

    @Override
    public void insertCompanies(List<Company> companies) {

    }
}
