package com.example.application.data.source.repository;

import com.example.application.data.Bug;
import com.example.application.data.source.local.BugLocalDataSource;
import com.example.application.data.source.remote.BugRemoteDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;

public class BugRepositoryImpl implements BugRepository {
    private HashMap<Integer, Bug> cachedBugs = new LinkedHashMap<>();

    private boolean isCacheDirty = false;

    private final BugRemoteDataSource bugsRemoteDataSource;

    private final BugLocalDataSource bugLocalDataSource;

    @Inject
    BugRepositoryImpl(BugRemoteDataSource bugsRemoteDataSource, BugLocalDataSource bugLocalDataSource) {
        this.bugsRemoteDataSource = bugsRemoteDataSource;
        this.bugLocalDataSource = bugLocalDataSource;
    }


    @Override
    public Flowable<List<Bug>> getBugs(boolean isFirstLoad) {
        if (!cachedBugs.isEmpty() && !isCacheDirty && isFirstLoad) {
            List<Bug> bugs = new ArrayList<>(cachedBugs.values());
            return Flowable.just(bugs);
        }

        if (!isFirstLoad) {
            return getBugsFromRemoteDataSource();
        }

        return bugLocalDataSource.getBugs()
                .flatMap((List<Bug> bugs) -> {
                            if (bugs.isEmpty()) {
                                return getBugsFromRemoteDataSource();
                            }
                            refreshCache(bugs);
                            isCacheDirty = false;
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

        return bugLocalDataSource.getBug(bugId)
                .flatMap((Optional<Bug> bug) -> {
                    if (!bug.isPresent()) {
                        return bugsRemoteDataSource.getBug(bugId);
                    }
                    return Flowable.just(bug);
                });
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
        bugLocalDataSource.insertBugs(bugs);
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
