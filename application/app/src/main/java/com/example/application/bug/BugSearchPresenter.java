package com.example.application.bug;

import com.example.application.data.source.repository.BugsRepository;

import javax.inject.Inject;
import javax.inject.Named;

public class BugSearchPresenter implements BugListContract.Presenter {
    @Named("bug_search")
    @Inject
    BugListContract.View view;

    private final BugsRepository bugsRepository;

    @Inject
    BugSearchPresenter(BugsRepository bugsRepository) {
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
