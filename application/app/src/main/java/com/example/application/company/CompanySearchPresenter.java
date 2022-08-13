package com.example.application.company;

import com.example.application.data.source.repository.CompanyRepository;

import java.util.Collections;

public class CompanySearchPresenter implements CompanySearchContract.Presenter {
    private final CompanyRepository companyRepository;

    private final CompanySearchContract.View view;

    public CompanySearchPresenter(CompanyRepository companyRepository, CompanySearchContract.View view) {
        this.companyRepository = companyRepository;
        this.view = view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void filterCompanies(String keyword) {
        view.showFilteredCompanies(companyRepository.filterCompanies(keyword));
    }
}
