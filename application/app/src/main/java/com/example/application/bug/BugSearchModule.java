package com.example.application.bug;

import com.example.application.data.source.repository.BugRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BugSearchModule {
    @Binds
    abstract BugSearchContract.View bindBugSearchFragment(BugSearchFragment bugSearchFragment);

    @Provides
    static BugSearchContract.Presenter provideBugSearchPresenter(BugRepository bugRepository, BugSearchContract.View view) {
        return new BugSearchPresenter(bugRepository, view);
    }
}
