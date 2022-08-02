package com.example.application.data.source;

import com.example.application.data.User;
import com.example.application.data.UserAuthentication;
import com.example.application.data.source.remote.UserLogIn;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface UserDataSource {
    Maybe<UserAuthentication> login(UserLogIn userInput);

    Completable delete();

    Maybe<User> getUserInformation();
}
