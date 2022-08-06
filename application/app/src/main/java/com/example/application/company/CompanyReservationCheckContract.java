package com.example.application.company;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.Bug;
import com.example.application.data.Company;
import com.example.application.data.Reservation;
import com.example.application.data.ReservationForm;
import com.example.application.data.User;

public interface CompanyReservationCheckContract {
    interface Presenter extends BasePresenter {
        void reserve(int companyId);

        void getReservationCheckInformation();

        void setCompanyId(int companyId);
    }

    interface View extends BaseView {
        void showLoadingErrorMessage(String message);

        void showReservationInformation(ReservationForm reservationForm);

        void showUserInformation(User user);

        void showCompanyInformation(Company company);

        void toastReservationErrorMessage(String message);
    }
}
