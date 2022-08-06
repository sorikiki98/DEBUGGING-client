package com.example.application.data.source.remote;

import com.example.application.data.Company;
import com.example.application.data.Reservation;
import com.example.application.data.ReservationForm;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CompanyService {
    @GET("companies")
    Flowable<List<Company>> getCompanies(@Header("Authorization") String token);

    @POST("companies/interest/{company_id}")
    Completable addCompanyInterest(@Path("company_id") int companyId, @Header("Authorization") String token);

    @DELETE("companies/interest/{company_id}")
    Completable removeCompanyInterest(@Path("company_id") int companyId, @Header("Authorization") String token);

    @POST("companies/reservation/{company_id}")
    Maybe<Integer> reserveCompany(@Path("company_id") int companyId, @Body ReservationForm reservationForm, @Header("Authorization") String token);

    @GET("companies/reservation/{reservation_id}")
    Maybe<Reservation> getReservationInformation(@Path("reservation_id") int reservationId, @Header("Authorization") String token);
}
