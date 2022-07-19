package com.example.application.data.repository;

import com.example.application.data.remote.api.BugsService;
import com.example.application.data.remote.response.Bug;
import com.example.application.data.repository.BugsRepository;

import java.util.List;

import javax.inject.Inject;

public class BugsRepositoryImpl implements BugsRepository {
    private List<Bug> bugs;
    private BugsService bugsService;

    @Inject
    public BugsRepositoryImpl(BugsService bugsService) {
        this.bugsService = bugsService;
    }

    @Override
    public List<Bug> getBugs() {
        return null;
    }

    @Override
    public Bug getBug(int bugId) {
        return null;
    }

    @Override
    public void survey(int bugId) {

    }
}
