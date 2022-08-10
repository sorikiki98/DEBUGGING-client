package com.example.application.mypage;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.MyReservation;
import com.example.application.data.Reservation;
import com.example.application.data.User;

import java.util.List;

public interface MyPageCompanyDetailListContract {
    interface Presenter extends BasePresenter {
        void loadReservedCompanies();
        void loadUserName();
    }

    interface View extends BaseView {
        void showReservedCompanies(List<MyReservation> reservation);
        void showUserName(String userName);
        void showErrorMessage(String message);
    }
}
