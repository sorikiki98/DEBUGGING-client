package com.example.application.home;

import com.example.application.bug.BugsListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class HomeMenuModule {
    @Binds
    abstract HomeContract.View bindHomeActivity(HomeActivity homeActivity);

//    @Provides
//    static HomeContract.Presenter provideHomePresenter(HomePresenter homePresenter) {
////        return new HomePresenter()
//    }
}
