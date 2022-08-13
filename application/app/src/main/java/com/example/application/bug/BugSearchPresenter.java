package com.example.application.bug;


import com.example.application.data.source.repository.BugRepository;

import java.util.Collections;

import javax.inject.Inject;
import javax.inject.Named;

public class BugSearchPresenter implements BugSearchContract.Presenter {
    private final BugRepository bugRepository;

    private final BugSearchContract.View view;

    @Inject
    BugSearchPresenter(BugRepository bugRepository, BugSearchContract.View view) {
        this.bugRepository = bugRepository;
        this.view = view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void filterBugs(String keyword) {
        view.showFilteredBugs(bugRepository.filterBugs(keyword));
    }
}
