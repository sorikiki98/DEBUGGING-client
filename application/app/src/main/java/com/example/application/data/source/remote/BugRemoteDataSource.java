package com.example.application.data.source.remote;

import com.example.application.PreferencesManager;
import com.example.application.data.Bug;
import com.example.application.data.source.BugDataSource;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;

public class BugRemoteDataSource implements BugDataSource {
    private final BugService bugsApi;

    private final Scheduler ioScheduler;

    private final PreferencesManager preferencesManager;

    @Inject
    public BugRemoteDataSource(BugService bugService, Scheduler scheduler, PreferencesManager preferencesManager) {
        this.bugsApi = bugService;
        this.ioScheduler = scheduler;
        this.preferencesManager = preferencesManager;
    }

    @Override
    public Flowable<List<Bug>> getBugs() {
        return bugsApi.getBugs(getAuthToken())
                .subscribeOn(ioScheduler);
    }

    @Override
    public Flowable<Optional<Bug>> getBug(int bugId) {
        return bugsApi.getBug(bugId, getAuthToken())
                .subscribeOn(ioScheduler);
    }

    @Override
    public void insertBugs(List<Bug> bugs) {

    }

    @Override
    public void survey() {

    }

    private String getAuthToken() {
        return "Bearer " + this.preferencesManager.fetchAuthToken();
    }
}
