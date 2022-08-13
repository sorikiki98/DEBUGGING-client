package com.example.application.mypage;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.MyProduct;
import com.example.application.data.MySurvey;

import java.util.List;

public interface MyPageSurveyDetailListContract {interface Presenter extends BasePresenter {
    void getMyPageSurveyList();

    void refreshMyPageSurveyList();

    void getUserName();
}

    interface View extends BaseView {
        void showMyPageSurveyList(List<MySurvey> mySurveyList);

        void showUserName(String userName);

        void showErrorMessage(String message);

        void undoRefreshLoading();
    }
}
