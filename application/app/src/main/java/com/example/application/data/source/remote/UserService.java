package com.example.application.data.source.remote;

import com.example.application.data.RegistrationForm;
import com.example.application.data.User;
import com.example.application.data.UserAuthentication;
import com.example.application.data.UserLogIn;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {
    @POST("user/login")
    Maybe<UserAuthentication> logIn(@Body UserLogIn userLogIn);

    @POST("user/signup")
    Maybe<UserAuthentication> signup(@Body RegistrationForm registrationForm);

    @DELETE("user")
    Completable delete(@Header("Authorization") String token);

    @GET("user/mypage")
    Maybe<User> getUserInformation(@Header("Authorization") String token);
}
