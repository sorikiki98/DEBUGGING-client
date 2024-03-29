package com.example.application.bug;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.BugRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BugItemModule {
    @Binds
    abstract BugItemContract.View bindBugsItemFragment(BugItemFragment fragment);

    @Provides
    static BugItemContract.Presenter provideBugsItemPresenter(BugRepository repository, BugItemContract.View view, SchedulersFacade scheduler) {
        return new BugItemPresenter(repository, view, scheduler.ui());
    }
}
