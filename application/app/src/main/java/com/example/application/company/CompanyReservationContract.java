package com.example.application.company;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.ReservationForm;
import com.example.application.data.User;

public interface CompanyReservationContract {
    interface Presenter extends BasePresenter {
        void storeReservationForm(ReservationForm reservationForm);

        void loadUserInformation();

        void setCompanyId(int companyId);
    }

    interface View extends BaseView {
        void showUserInformation(User user);
    }
}
