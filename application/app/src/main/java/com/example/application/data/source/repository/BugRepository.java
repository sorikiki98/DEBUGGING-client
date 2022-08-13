package com.example.application.data.source.repository;

import com.example.application.data.Bug;

import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Response;

public interface BugRepository {
    Flowable<List<Bug>> getBugs(boolean isFirstLoad);

    Flowable<Optional<Bug>> getBug(int bugId);

    void refreshCache(List<Bug> bugs);

    void refreshLocalDataSource(List<Bug> bugs);

    void survey(int bugId);
}
