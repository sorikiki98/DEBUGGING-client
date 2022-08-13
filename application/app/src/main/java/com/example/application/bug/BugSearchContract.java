package com.example.application.bug;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.Bug;

import java.util.List;

public interface BugSearchContract {
    interface Presenter extends BasePresenter {
        void filterBugs(String keyword);
    }

    interface View extends BaseView {
        void showFilteredBugs(List<Bug> bugs);
    }
}
