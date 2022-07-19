package com.example.application.bug;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BugsViewModule {
    @Named("bug_list")
    @Binds
    abstract BugsContract.View bindBugsListFragment(BugsListFragment bugsListFragment);

    @Named("bug_item")
    @Binds
    abstract BugsContract.View bindBugsItemFragment(BugsItemFragment bugsItemFragment);

    @Named("bug_search")
    @Binds
    abstract BugsContract.View bindBugsSearchFragment(BugsSearchFragment bugsSearchFragment);
}
