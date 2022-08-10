package com.example.application.mypage;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.UserRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.core.Scheduler;

@Module
public abstract class MyPageCompanyDetailListModule {
    @Binds
    abstract MyPageCompanyDetailListContract.View bindMyPageCompanyDetailListFragment(MyPageCompanyDetailListFragment myPageCompanyDetailListFragment);

    @Provides
    static MyPageCompanyDetailListContract.Presenter provideMyPageCompanyDetailListPresenter(UserRepository repository, MyPageCompanyDetailListContract.View view, SchedulersFacade scheduler) {
        return new MyPageCompanyDetailListPresenter(repository, view, scheduler.ui());
    }
}
