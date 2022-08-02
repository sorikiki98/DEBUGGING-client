package com.example.application.data.source.repository;

import android.util.Log;

import com.example.application.data.Bug;
import com.example.application.data.source.BugsDataSource;
import com.example.application.data.source.local.BugsLocalDataSource;
import com.example.application.data.source.remote.BugsRemoteDataSource;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class BugsRepositoryImpl implements BugsRepository {
    private HashMap<Integer, Bug> cachedBugs = new LinkedHashMap<>();

    private boolean isCacheDirty = false;

    private final BugsRemoteDataSource bugsRemoteDataSource;

    private final BugsLocalDataSource bugsLocalDataSource;

    @Inject
    BugsRepositoryImpl(BugsRemoteDataSource bugsRemoteDataSource, BugsLocalDataSource bugsLocalDataSource) {
        this.bugsRemoteDataSource = bugsRemoteDataSource;
        this.bugsLocalDataSource = bugsLocalDataSource;
    }


    @Override
    public Flowable<List<Bug>> getBugs() {
        if (!cachedBugs.isEmpty() && !isCacheDirty) {
            List<Bug> bugs = new ArrayList<>(cachedBugs.values());
            return Flowable.just(bugs);
        }

        if (isCacheDirty) {
            return getBugsFromRemoteDataSource();
        }

        return bugsLocalDataSource.getBugs()
                .flatMap((List<Bug> bugs) -> {
                            if (bugs.isEmpty()) {
                                return getBugsFromRemoteDataSource();
                            }
                            refreshCache(bugs);
                            return Flowable.just(bugs);
                        }
                );
    }

    @Override
    public Flowable<Optional<Bug>> getBug(int bugId) {
        if (!cachedBugs.isEmpty() && !isCacheDirty) {
            Optional<Bug> bug = Optional.ofNullable(cachedBugs.get(bugId));
            return Flowable.just(bug);
        }

        if (isCacheDirty) {
            return bugsRemoteDataSource.getBug(bugId);
        }

        return bugsLocalDataSource.getBug(bugId)
                .flatMap((Optional<Bug> bug) -> {
                    if (!bug.isPresent()) {
                        return bugsRemoteDataSource.getBug(bugId);
                    }
                    return Flowable.just(bug);
                });
    }

    @Override
    public void refreshBugs() {
        isCacheDirty = true;
    }

    @Override
    public void refreshCache(List<Bug> bugs) {
        cachedBugs = new LinkedHashMap<>();
        for (Bug bug : bugs) {
            cachedBugs.put(bug.getId(), bug);
        }
    }

    @Override
    public void refreshLocalDataSource(List<Bug> bugs) {
        bugsLocalDataSource.insertBugs(bugs);
    }

    @Override
    public void survey(int bugId) {

    }

    private Flowable<List<Bug>> getBugsFromRemoteDataSource() {
        return bugsRemoteDataSource.getBugs()
                .flatMap((List<Bug> bugs) -> {
                    refreshCache(bugs);
                    refreshLocalDataSource(bugs);
                    isCacheDirty = false;
                    return Flowable.just(bugs);
                });
    }
}
