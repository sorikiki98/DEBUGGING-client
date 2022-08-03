package com.example.application.data.source.repository;

import com.example.application.data.User;
import com.example.application.data.UserAuthentication;
import com.example.application.data.source.remote.UserLogIn;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;

public interface UserRepository {
    Maybe<UserAuthentication> login(UserLogIn userInput);

    Maybe<User> loadUserInformation();

    Completable delete();

    Completable logout();

    void refreshMyPage();

    void refreshCache(User userInfo);

    String getUserName();

    Maybe<Integer> getAccumulatedNumOfUsages();

    String getAuthToken();
}
