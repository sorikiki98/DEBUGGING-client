package com.example.application.data.source.local;

import com.example.application.SchedulersFacade;
import com.example.application.data.Bug;
import com.example.application.data.source.BugDataSource;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;

public class BugLocalDataSource implements BugDataSource {
    private final BugDao bugDao;

    private final Scheduler ioScheduler;

    @Inject
    public BugLocalDataSource(BugDao bugDao, SchedulersFacade schedulersFacade) {
        this.bugDao = bugDao;
        this.ioScheduler = schedulersFacade.io();
    }

    @Override
    public Flowable<List<Bug>> getBugs() {
        return bugDao.getBugs()
                .subscribeOn(ioScheduler);
    }

    @Override
    public Flowable<Optional<Bug>> getBug(int bugId) {
        return bugDao.getBug(bugId)
                .subscribeOn(ioScheduler);
    }


    @Override
    public void insertBugs(List<Bug> bugs) {
        bugDao.insertBugs(bugs);
    }

    @Override
    public void survey() {

    }
}
