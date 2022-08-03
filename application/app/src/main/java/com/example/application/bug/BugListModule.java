package com.example.application.bug;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.BugsRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BugListModule {
    @Binds
    abstract BugListContract.View bindBugsListFragment(BugsListFragment fragment);

    @Provides
    static BugListContract.Presenter provideBugsListPresenter(BugsRepository repository, BugListContract.View view, SchedulersFacade scheduler) {
        return new BugListPresenter(repository, view, scheduler.ui());
    }
}
