package com.example.application.mypage;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.UserRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MyPageProductInterestListModule {
    @Binds
    abstract MyPageProductInterestListContract.View bindMyPageProductInterestListFragment(MyPageProductInterestListFragment myPageProductInterestListFragment);

    @Provides
    static MyPageProductInterestListContract.Presenter provideMyPageProductInterestListPresenter(UserRepository userRepository, MyPageProductInterestListContract.View view, SchedulersFacade scheduler) {
        return new MyPageProductInterestListPresenter(userRepository, view, scheduler.ui());
    }
}
