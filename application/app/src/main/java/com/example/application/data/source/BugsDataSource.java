package com.example.application.data.source;

import com.example.application.data.Bug;

import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.core.Flowable;


public interface BugsDataSource {
    public Flowable<List<Bug>> getBugs();

    public Flowable<Optional<Bug>> getBug(int bugId);

    void insertBugs(List<Bug> bugs);

    void survey();
}
