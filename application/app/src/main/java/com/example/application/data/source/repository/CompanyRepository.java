package com.example.application.data.source.repository;

import com.example.application.data.Company;
import com.example.application.data.Reservation;
import com.example.application.data.ReservationForm;

import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public interface CompanyRepository {
    Flowable<List<Company>> getCompanies();

    Flowable<Optional<Company>> getCompanyWithId(int companyId);

    Completable addCompanyInterest(int companyId);

    Completable removeCompanyInterest(int companyId);

    Maybe<Integer> reserveCompany(int companyId);

    Maybe<Reservation> getReservationInformation(int reservationId);

    boolean isCompanyInterested(int companyId);

    void keepReservationForm(ReservationForm reservationForm);

    ReservationForm getReservationForm();

    void refreshCompanies();

    void refreshCache(List<Company> companies);

    void refreshLocalDataSource(List<Company> companies);
}
