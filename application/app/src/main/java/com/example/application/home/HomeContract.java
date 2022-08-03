package com.example.application.home;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.Bug;

import java.util.List;

public interface HomeContract {
    interface Presenter extends BasePresenter {
        void setUserName();

        void setAccumulatedNumberOfUsages();

        void logout();

        void signOut();
    }

    interface View extends BaseView {

        void showUserName(String userName);

        void showAccumulatedNumberOfUsages(int total);

        void goBackToHomeScreen();
    }
}
