package com.example.application.bug;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.BugsRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BugsItemModule {
    @Binds
    abstract BugsItemContract.View bindBugsItemFragment(BugsItemFragment fragment);

    @Provides
    static BugsItemContract.Presenter provideBugsItemPresenter(BugsRepository repository, BugsItemContract.View view, SchedulersFacade scheduler) {
        return new BugsItemPresenter(repository, view, scheduler.ui());
    }
}
