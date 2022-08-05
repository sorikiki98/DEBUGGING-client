package com.example.application.data.source;

import com.example.application.data.Company;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface CompanyDataSource {
    public Flowable<List<Company>> getCompanies();

    public Completable addCompanyInterest(int companyId);

    public Completable removeCompanyInterest(int companyId);

    public void insertCompanies(List<Company> companies);
}
