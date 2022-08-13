package com.example.application.bug;


import com.example.application.data.source.repository.BugRepository;

import javax.inject.Inject;
import javax.inject.Named;

public class BugSearchPresenter implements BugListContract.Presenter {
    @Named("bug_search")
    @Inject
    BugListContract.View view;

    private final BugRepository bugRepository;

    @Inject
    BugSearchPresenter(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void getBugs() {

    }

    @Override
    public void refreshBugs() {

    }
}
