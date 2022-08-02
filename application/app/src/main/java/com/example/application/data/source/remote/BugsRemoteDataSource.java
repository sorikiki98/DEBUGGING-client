package com.example.application.data.source.remote;

import android.util.Log;

import com.example.application.data.Bug;
import com.example.application.data.source.BugsDataSource;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BugsRemoteDataSource implements BugsDataSource {
    private final BugsService bugsApi;

    private final Scheduler ioScheduler;

    @Inject
    public BugsRemoteDataSource(BugsService bugsService, Scheduler scheduler) {
        this.bugsApi = bugsService;
        this.ioScheduler = scheduler;
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
