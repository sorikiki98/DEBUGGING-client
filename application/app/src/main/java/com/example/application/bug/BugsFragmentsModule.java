package com.example.application.bug;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BugsFragmentsModule {
    @ContributesAndroidInjector(modules = BugsListModule.class)
    abstract BugsListFragment contributeBugsListFragment();

    @ContributesAndroidInjector(modules = BugsItemModule.class)
    abstract BugsItemFragment contributeBugsItemFragment();
}
