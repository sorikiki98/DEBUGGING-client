package com.example.application.bug;

import com.example.application.data.remote.response.Bug;
import com.example.application.data.repository.BugsRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class BugsListPresenter implements BugsContract.Presenter {
    @Inject
    BugsRepository bugsRepository;

    @Named("bug_list")
    @Inject
    BugsContract.View view;

    @Inject
    BugsListPresenter(BugsRepository bugsRepository, BugsContract.View view) {
        this.bugsRepository = bugsRepository;
        this.view = view;
    }

    @Override
    public List<Bug> getBugs() {
        return null;
    }

    @Override
    public Bug getBug() {
        return null;
    }

    @Override
    public void survey() {

    }
}
