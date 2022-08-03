package com.example.application.data.source.remote;

import android.util.Log;

import com.example.application.PreferencesManager;
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

    private final PreferencesManager preferencesManager;

    @Inject
    public BugsRemoteDataSource(BugsService bugsService, Scheduler scheduler, PreferencesManager preferencesManager) {
        this.bugsApi = bugsService;
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
