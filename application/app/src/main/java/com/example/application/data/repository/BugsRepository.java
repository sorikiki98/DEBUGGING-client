package com.example.application.data.repository;

import com.example.application.data.remote.response.Bug;

import java.util.List;

public interface BugsRepository {
    List<Bug> getBugs();

    Bug getBug(int bugId);

    void survey(int bugId);
}
