package com.example.application.data.api;

import com.example.application.data.request.UserLogIn;
import com.example.application.data.response.UserAuthentication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsersService {
    @POST("user/login")
    Call<UserAuthentication> logIn(@Body UserLogIn userLogIn);
}
