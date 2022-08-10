package com.example.application.mypage;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.UserRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.core.Scheduler;

@Module
public abstract class MyPageMainModule {
    @Binds
    abstract MyPageMainContract.View bindMyPageMainFragment(MyPageMainFragment myPageMainFragment);

    @Provides
    static MyPageMainContract.Presenter provideMyPageMainPresenter(UserRepository userRepository, MyPageMainContract.View view, SchedulersFacade scheduler) {
        return new MyPageMainPresenter(userRepository, view, scheduler.ui());
    }
}
