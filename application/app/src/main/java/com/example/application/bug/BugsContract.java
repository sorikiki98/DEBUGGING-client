package com.example.application.bug;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.remote.response.Bug;

import java.util.List;

public interface BugsContract {
    interface Presenter extends BasePresenter {
        List<Bug> getBugs();

        Bug getBug();

        void survey();
    }

    interface View extends BaseView {


        void showLoadingIndicator();

        void hideLoadingIndicator();
    }
}
