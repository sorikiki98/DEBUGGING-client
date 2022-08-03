package com.example.application.data.source.remote;

import androidx.work.impl.model.Preference;

import com.example.application.PreferencesManager;
import com.example.application.data.User;
import com.example.application.data.UserAuthentication;
import com.example.application.data.source.UserDataSource;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Scheduler;

public class UserRemoteDataSource implements UserDataSource {
    private final UsersService usersService;

    private final Scheduler ioScheduler;

    private final PreferencesManager preferencesManager;

    @Inject
    public UserRemoteDataSource(UsersService usersService, Scheduler scheduler, PreferencesManager preferencesManager) {
        this.usersService = usersService;
        this.ioScheduler = scheduler;
        this.preferencesManager = preferencesManager;
    }

    @Override
    public Maybe<UserAuthentication> login(UserLogIn userInput) {
        return usersService.logIn(userInput)
                .subscribeOn(ioScheduler);
    }

    @Override
    public Completable delete() {
        return usersService.delete(getAuthToken())
                .subscribeOn(ioScheduler);
    }

    @Override
    public Maybe<User> getUserInformation() {
        return usersService.getUserInformation(getAuthToken())
                .subscribeOn(ioScheduler);
    }

    private String getAuthToken() {
        return "Bearer " + this.preferencesManager.fetchAuthToken();
    }
}
