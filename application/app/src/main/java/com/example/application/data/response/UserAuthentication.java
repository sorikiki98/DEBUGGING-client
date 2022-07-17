package com.example.application.data.response;

public final class UserAuthentication {
    private final String userName;
    private final String token;

    UserAuthentication(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getUserName() {
        return userName;
    }
}
