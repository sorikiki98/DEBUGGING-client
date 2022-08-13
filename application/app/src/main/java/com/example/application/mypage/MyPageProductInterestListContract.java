package com.example.application.mypage;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.MyProduct;
import com.example.application.data.User;

import java.util.List;

public interface MyPageProductInterestListContract {
    interface Presenter extends BasePresenter {
        void getMyPageProductInterest();

        void refreshMyPageProductInterest();

        void getUserName();
    }

    interface View extends BaseView {
        void showMyPageProductInterest(List<MyProduct> myProductList);

        void showUserName(String userName);

        void showErrorMessage(String message);

        void undoRefreshLoading();
    }
}
