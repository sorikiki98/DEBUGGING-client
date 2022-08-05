package com.example.application.data.source.remote;

import com.example.application.PreferencesManager;
import com.example.application.data.User;
import com.example.application.data.UserAuthentication;
import com.example.application.data.source.UserDataSource;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Scheduler;

public class UserRemoteDataSource implements UserDataSource {
    private final UserService userService;

    private final Scheduler ioScheduler;

    private final PreferencesManager preferencesManager;

    @Inject
    public UserRemoteDataSource(UserService userService, Scheduler scheduler, PreferencesManager preferencesManager) {
        this.userService = userService;
        this.ioScheduler = scheduler;
        this.preferencesManager = preferencesManager;
    }

    @Override
    public Maybe<UserAuthentication> login(UserLogIn userInput) {
        return userService.logIn(userInput)
                .subscribeOn(ioScheduler);
    }

    @Override
    public Completable delete() {
        return userService.delete(getAuthToken())
                .subscribeOn(ioScheduler);
    }

    @Override
    public Maybe<User> getUserInformation() {
        return userService.getUserInformation(getAuthToken())
                .subscribeOn(ioScheduler);
    }

    private String getAuthToken() {
        return "Bearer " + this.preferencesManager.fetchAuthToken();
    }
}
