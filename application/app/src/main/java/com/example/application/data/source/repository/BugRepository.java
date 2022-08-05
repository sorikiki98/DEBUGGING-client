package com.example.application.data.source.repository;

import com.example.application.data.Bug;

import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.core.Flowable;

public interface BugRepository {
    Flowable<List<Bug>> getBugs();

    Flowable<Optional<Bug>> getBug(int bugId);

    void refreshBugs();

    void refreshCache(List<Bug> bugs);

    void refreshLocalDataSource(List<Bug> bugs);

    void survey(int bugId);
}
