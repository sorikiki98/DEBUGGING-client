package com.example.application.home;

import android.content.Context;

import com.example.application.SchedulersFacade;
import com.example.application.bug.BugsListFragment;
import com.example.application.data.source.repository.UserRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.core.Scheduler;

@Module
public abstract class HomeModule {
    @Binds
    abstract HomeContract.View bindHomeActivity(HomeActivity homeActivity);

    @Provides
    static HomeContract.Presenter provideHomePresenter(UserRepository userRepository, HomeContract.View view, SchedulersFacade scheduler, Context context) {
        return new HomePresenter(userRepository, view, scheduler.ui(), scheduler.io(), context);
    }
}
