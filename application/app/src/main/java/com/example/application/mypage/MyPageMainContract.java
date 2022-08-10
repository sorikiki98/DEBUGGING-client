package com.example.application.mypage;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.User;

public interface MyPageMainContract {
    interface Presenter extends BasePresenter {
        void loadUserInfo();
    }

    interface View extends BaseView {
        void showUserInfo(User user);
        void showErrorMessage(String message);
    }
}
