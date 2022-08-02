package com.example.application.bug;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.Bug;

import java.util.List;

public interface BugsItemContract {
    interface Presenter extends BasePresenter {
        void setBugId(int bugId);
        void loadBugWithId(int bugId);
    }

    interface View extends BaseView {

        void showBug(Bug bug);

        void showErrorMessage(String message);
    }
}
