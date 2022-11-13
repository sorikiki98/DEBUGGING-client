package com.example.application.data.source.remote;

import com.example.application.PreferencesManager;
import com.example.application.SchedulersFacade;
import com.example.application.data.Bug;
import com.example.application.data.source.BugDataSource;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import retrofit2.HttpException;

public class BugRemoteDataSource implements BugDataSource {
    private final BugService bugsApi;

    private final Scheduler ioScheduler;

    @Inject
    public BugRemoteDataSource(BugService bugService, SchedulersFacade schedulersFacade) {
        this.bugsApi = bugService;
        this.ioScheduler = schedulersFacade.io();
    }

    @Override
    public Flowable<List<Bug>> getBugs() {
        return bugsApi.getBugs()
                .subscribeOn(ioScheduler);
    }

    @Override
    public Flowable<Optional<Bug>> getBug(int bugId) {
        return bugsApi.getBug(bugId)
                .subscribeOn(ioScheduler);
    }

    @Override
    public void insertBugs(List<Bug> bugs) {

    }

    @Override
    public void survey() {

    }
}
