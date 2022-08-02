package com.example.application.data.source.local;

import com.example.application.data.Bug;
import com.example.application.data.source.BugsDataSource;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;

public class BugsLocalDataSource implements BugsDataSource {
    private final BugsDao bugsDao;

    private final Scheduler ioScheduler;

    @Inject
    public BugsLocalDataSource(BugsDao bugsDao, Scheduler scheduler) {
        this.bugsDao = bugsDao;
        this.ioScheduler = scheduler;
    }

    @Override
    public Flowable<List<Bug>> getBugs() {
        return bugsDao.getBugs()
                .subscribeOn(ioScheduler);
    }

    @Override
    public Flowable<Optional<Bug>> getBug(int bugId) {
        return bugsDao.getBug(bugId)
                .subscribeOn(ioScheduler);
    }


    @Override
    public void insertBugs(List<Bug> bugs) {
        bugsDao.insertBugs(bugs);
    }

    @Override
    public void survey() {

    }
}
