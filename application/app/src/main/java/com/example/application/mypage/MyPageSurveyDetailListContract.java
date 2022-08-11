package com.example.application.mypage;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.MyProduct;
import com.example.application.data.MySurvey;

import java.util.List;

public interface MyPageSurveyDetailListContract {interface Presenter extends BasePresenter {
    void loadMyPageSurveyList();
    void loadUserName();
}

    interface View extends BaseView {
        void showMyPageSurveyList(List<MySurvey> mySurveyList);
        void showUserName(String userName);
        void showErrorMessage(String message);
    }
}
