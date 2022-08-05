package com.example.application.company;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.Company;

import java.util.List;

public interface CompanyItemContract {
    interface Presenter extends BasePresenter {
        void setCompanyId(int companyId);

        void loadCompany();
    }

    interface View extends BaseView {

        void showCompany(Company company);

        void showErrorMessage(String message);

    }
}
