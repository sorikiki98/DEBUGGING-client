package com.example.application.data.source.repository;

import com.example.application.data.Company;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface CompanyRepository {
    Flowable<List<Company>> getCompanies();

    Completable addCompanyInterest(int companyId);

    Completable removeCompanyInterest(int companyId);

    boolean isCompanyInterested(int companyId);

    void refreshCompanies();

    void refreshCache(List<Company> companies);

    void refreshLocalDataSource(List<Company> companies);
}
