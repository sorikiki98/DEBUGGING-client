package com.example.application.bug;

import com.example.application.data.source.repository.BugsRepository;

import javax.inject.Inject;
import javax.inject.Named;

public class BugsSearchPresenter implements BugsListContract.Presenter {
    @Named("bug_search")
    @Inject
    BugsListContract.View view;

    private final BugsRepository bugsRepository;

    @Inject
    BugsSearchPresenter(BugsRepository bugsRepository) {
        this.bugsRepository = bugsRepository;
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadBugs() {

    }

    @Override
    public void loadBugWithId() {

    }

    @Override
    public void survey() {

    }
}
