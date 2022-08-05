package com.example.application.bug;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.BugRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BugListModule {
    @Binds
    abstract BugListContract.View bindBugsListFragment(BugListFragment fragment);

    @Provides
    static BugListContract.Presenter provideBugsListPresenter(BugRepository repository, BugListContract.View view, SchedulersFacade scheduler) {
        return new BugListPresenter(repository, view, scheduler.ui());
    }
}
