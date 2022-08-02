package com.example.application.login;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.source.remote.UserLogIn;

public interface LoginContract {
    interface Presenter extends BasePresenter {
        void login(UserLogIn userInput);
    }

    interface View extends BaseView {
        void processLoginSuccess();

        void processLoginFail();
    }
}
