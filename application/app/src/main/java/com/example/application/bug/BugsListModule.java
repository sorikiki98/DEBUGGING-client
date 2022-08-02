package com.example.application.bug;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.BugsRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BugsListModule {
    @Binds
    abstract BugsListContract.View bindBugsListFragment(BugsListFragment fragment);

    @Provides
    static BugsListContract.Presenter provideBugsListPresenter(BugsRepository repository, BugsListContract.View view, SchedulersFacade scheduler) {
        return new BugsListPresenter(repository, view, scheduler.ui());
    }
}
