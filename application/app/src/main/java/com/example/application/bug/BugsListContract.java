package com.example.application.bug;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.Bug;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public interface BugsListContract {
    interface Presenter extends BasePresenter {
        void loadBugs();

        void loadBugWithId();

        void survey();
    }

    interface View extends BaseView {

        void showBugs(List<Bug> bugs);

        void showErrorMessage(String message);
    }
}
