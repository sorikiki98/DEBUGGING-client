package com.example.application.data.source.remote;

import com.example.application.data.User;
import com.example.application.data.UserAuthentication;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsersService {
    @POST("user/login")
    Maybe<UserAuthentication> logIn(@Body UserLogIn userLogIn);

    @DELETE("user")
    Completable delete();

    @GET("user/mypage")
    Maybe<User> getUserInformation();
}
