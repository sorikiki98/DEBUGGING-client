package com.example.application.mypage;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.UserRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MyPageSurveyDetailListModule {
    @Binds
    abstract MyPageSurveyDetailListContract.View bindMyPageSurveyListFragment(MyPageSurveyDetailListFragment myPageSurveyDetailListFragment);

    @Provides
    static MyPageSurveyDetailListContract.Presenter provideMyPageSurveyListPresenter(UserRepository userRepository, MyPageSurveyDetailListContract.View view, SchedulersFacade scheduler) {
        return new MyPageSurveyDetailListPresenter(userRepository, view, scheduler.ui());
    }
}
