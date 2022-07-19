package com.example.application.bug;

import com.example.application.data.repository.BugsRepository;
import com.example.application.data.repository.BugsRepositoryImpl;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class BugsModule {
    @Binds
    abstract BugsRepository bindBugsRepository(BugsRepositoryImpl repository);

    @Named("bug_list")
    @Provides
    static BugsContract.Presenter provideBugsListPresenter(BugsRepository bugsRepository, BugsContract.View view) {
        return new BugsListPresenter(bugsRepository, view);
    }

    @Named("bug_item")
    @Provides
    static BugsContract.Presenter provideBugsItemPresenter(BugsRepository bugsRepository, BugsContract.View view) {
        return new BugsItemPresenter(bugsRepository, view);
    }

    @Named("bug_search")
    @Provides
    static BugsContract.Presenter provideBugsSearchPresenter(BugsRepository bugsRepository, BugsContract.View view) {
        return new BugsSearchPresenter(bugsRepository, view);
    }
}
