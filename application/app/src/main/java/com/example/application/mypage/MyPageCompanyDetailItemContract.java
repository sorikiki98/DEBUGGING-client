package com.example.application.mypage;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.MyReservation;
import com.example.application.data.Reservation;

import java.util.List;

public interface MyPageCompanyDetailItemContract {
    interface Presenter extends BasePresenter {
        void loadReservationInformation();

        void setReservationId(int reservationId);
    }

    interface View extends BaseView {
        void showReservationInformation(Reservation reservation);

        void showErrorMessage(String message);
    }
}
