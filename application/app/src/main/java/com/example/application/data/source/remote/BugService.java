package com.example.application.data.source.remote;

import com.example.application.data.Bug;
import java.util.List;
import java.util.Optional;


import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BugService {
    @GET("bugs")
    Flowable<List<Bug>> getBugs();

    @GET("bugs/{bug_id}")
    Flowable<Optional<Bug>> getBug(@Path("bug_id")int bugId);

    @POST("bugs/survey/{bug_id}")
    void survey(@Path("bug_id")int bugId);
}
