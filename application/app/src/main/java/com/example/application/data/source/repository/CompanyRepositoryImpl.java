package com.example.application.data.source.repository;

import android.util.Log;

import com.example.application.data.Company;
import com.example.application.data.Reservation;
import com.example.application.data.ReservationForm;
import com.example.application.data.source.local.CompanyLocalDataSource;
import com.example.application.data.source.remote.CompanyRemoteDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

@Singleton
public class CompanyRepositoryImpl implements CompanyRepository {
    private HashMap<Integer, Company> cachedCompanies = new LinkedHashMap<>();

    private ReservationForm reservationForm;

    private boolean isCacheDirty = false;

    private boolean isFirstLoad = true;

    private final CompanyRemoteDataSource companyRemoteDataSource;

    private final CompanyLocalDataSource companyLocalDataSource;


    @Inject
    CompanyRepositoryImpl(CompanyRemoteDataSource companyRemoteDataSource, CompanyLocalDataSource companyLocalDataSource, UserRepository userRepository) {
        this.companyRemoteDataSource = companyRemoteDataSource;
        this.companyLocalDataSource = companyLocalDataSource;
    }


    @Override
    public Flowable<List<Company>> getCompanies() {
        if (!isCacheDirty && !cachedCompanies.isEmpty()) {
            return Flowable.just(new ArrayList<>(cachedCompanies.values()));
        }

        if (isCacheDirty && isFirstLoad) {
            return getCompaniesFromRemoteDataSource();
        }

        return companyLocalDataSource.getCompanies()
                .flatMap((List<Company> companies) -> {
                    if (companies.isEmpty()) {
                        return getCompaniesFromRemoteDataSource();
                    }
                    refreshCache(companies);
                    isCacheDirty = false;
                    return Flowable.just(companies);
                });
    }

    @Override
    public Flowable<Optional<Company>> getCompanyWithId(int companyId) {
        if (!isCacheDirty && !cachedCompanies.isEmpty()) {
            Optional<Company> company = Optional.ofNullable(cachedCompanies.get(companyId));
            return Flowable.just(company);
        }

        return companyLocalDataSource.getCompanyWithId(companyId);
    }

    @Override
    public Completable addCompanyInterest(int companyId) {
        return companyRemoteDataSource.addCompanyInterest(companyId)
                .andThen(companyLocalDataSource.addCompanyInterest(companyId));
    }

    @Override
    public Completable removeCompanyInterest(int companyId) {
        return companyRemoteDataSource.removeCompanyInterest(companyId)
                .andThen(companyLocalDataSource.removeCompanyInterest(companyId));
    }

    @Override
    public Maybe<Integer> reserveCompany(int companyId) {
        return companyRemoteDataSource.reserveCompany(companyId, reservationForm)
                .doAfterSuccess((reservationId) -> reservationForm = null);
    }

    @Override
    public Maybe<Reservation> getReservationInformation(int reservationId) {
        return companyRemoteDataSource.getReservationInformation(reservationId);
    }

    @Override
    public ReservationForm getReservationForm() {
        return this.reservationForm;
    }

    @Override
    public boolean isCompanyInterested(int companyId) {
        if (cachedCompanies != null && cachedCompanies.get(companyId) != null) {
            int isCompanyInterested = Objects.requireNonNull(cachedCompanies.get(companyId)).isCompanyInterested;
            return isCompanyInterested == 1;
        }
        return false;
    }

    @Override
    public void keepReservationForm(ReservationForm reservationForm) {
        Log.d("keepReservationForm", reservationForm.toString());
        this.reservationForm = reservationForm;
    }

    @Override
    public void refreshCompanies() {
        isCacheDirty = false;
        isFirstLoad = true;
        getCompanies();
    }

    @Override
    public void refreshCache(List<Company> companies) {
        cachedCompanies = new LinkedHashMap<>();
        for (Company company : companies) {
            cachedCompanies.put(company.id, company);
        }
    }

    @Override
    public void refreshLocalDataSource(List<Company> companies) {
        companyLocalDataSource.insertCompanies(companies);
    }

    private Flowable<List<Company>> getCompaniesFromRemoteDataSource() {
        Log.d("CompanyRepository", "remote");
        return companyRemoteDataSource.getCompanies()
                .flatMap((List<Company> companies) -> {
                    refreshLocalDataSource(companies);
                    refreshCache(companies);
                    isCacheDirty = false;
                    isFirstLoad = false;
                    return Flowable.just(companies);
                });
    }

}