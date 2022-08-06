package com.example.application.data.source;

import com.example.application.data.Company;
import com.example.application.data.Reservation;
import com.example.application.data.ReservationForm;

import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public interface CompanyDataSource {
    public Flowable<List<Company>> getCompanies();

    public Flowable<Optional<Company>> getCompanyWithId(int companyId);

    public Completable addCompanyInterest(int companyId);

    public Completable removeCompanyInterest(int companyId);

    public Maybe<Integer> reserveCompany(int companyId, ReservationForm reservationForm);

    public Maybe<Reservation> getReservationInformation(int reservationId);

    public void insertCompanies(List<Company> companies);
}
