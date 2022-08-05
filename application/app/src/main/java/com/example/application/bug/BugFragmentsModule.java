package com.example.application.bug;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BugFragmentsModule {
    @ContributesAndroidInjector(modules = BugListModule.class)
    abstract BugListFragment contributeBugsListFragment();

    @ContributesAndroidInjector(modules = BugItemModule.class)
    abstract BugItemFragment contributeBugsItemFragment();
}
