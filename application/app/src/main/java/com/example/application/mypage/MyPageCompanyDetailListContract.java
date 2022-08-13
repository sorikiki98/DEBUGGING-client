package com.example.application.mypage;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.MyReservation;
import com.example.application.data.Reservation;
import com.example.application.data.User;

import java.util.List;

public interface MyPageCompanyDetailListContract {
    interface Presenter extends BasePresenter {
        void getReservedCompanies();

        void refreshReservedCompanies();

        void getUserName();
    }

    interface View extends BaseView {
        void showReservedCompanies(List<MyReservation> reservation);

        void showUserName(String userName);

        void showErrorMessage(String message);

        void undoRefreshLoading();
    }
}
