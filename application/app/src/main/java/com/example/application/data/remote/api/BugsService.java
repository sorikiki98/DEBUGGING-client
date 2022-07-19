package com.example.application.data.remote.api;

import com.example.application.data.remote.response.Bug;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BugsService {
    @GET("bugs")
    Call<List<Bug>> getBugs();

    @GET("bugs/{bug_id}")
    Call<Bug> getBug(@Path("bug_id")String bugId);
}
