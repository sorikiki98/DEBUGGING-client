package com.example.application.company;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.Bug;
import com.example.application.data.Company;

import java.util.List;

public interface CompanyListContract {
    interface Presenter extends BasePresenter {
        void loadCompanies();

        void toggleCompanyInterest(int companyId);
    }

    interface View extends BaseView {

        void showCompanies(List<Company> companies);

        void showErrorMessage(String message);

    }
}
