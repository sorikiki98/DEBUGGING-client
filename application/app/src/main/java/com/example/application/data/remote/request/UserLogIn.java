package com.example.application.data.remote.request;

import com.google.gson.annotations.SerializedName;

public final class UserLogIn {
    @SerializedName("userName")
    private final String userName;

    @SerializedName("password")
    private final String password;

    public UserLogIn(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
