package com.example.application.data.remote.api;

import com.example.application.data.remote.request.UserLogIn;
import com.example.application.data.remote.response.UserAuthentication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsersService {
    @POST("user/login")
    Call<UserAuthentication> logIn(@Body UserLogIn userLogIn);
}
