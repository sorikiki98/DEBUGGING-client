package com.example.application.company;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.Company;

import java.util.List;

public interface CompanySearchContract {
    interface Presenter extends BasePresenter {
        void filterCompanies(String keyword);
    }

    interface View extends BaseView {
        void showFilteredCompanies(List<Company> companies);
    }
}
