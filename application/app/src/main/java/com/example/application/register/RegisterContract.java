package com.example.application.register;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.Product;
import com.example.application.data.RegistrationForm;

import java.util.List;

public interface RegisterContract {
    interface Presenter extends BasePresenter {
        void register(RegistrationForm form);
    }

    interface View extends BaseView {
        void toastErrorMessage(String message);
    }
}
