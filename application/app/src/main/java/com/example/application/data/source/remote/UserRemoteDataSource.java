package com.example.application.data.source.remote;

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

    @Inject
    public UserRemoteDataSource(UsersService usersService, Scheduler scheduler) {
        this.usersService = usersService;
        this.ioScheduler = scheduler;
    }

    @Override
    public Maybe<UserAuthentication> login(UserLogIn userInput) {
        return usersService.logIn(userInput)
                .subscribeOn(ioScheduler);
    }

    @Override
    public Completable delete() {
        return usersService.delete()
                .subscribeOn(ioScheduler);
    }

    @Override
    public Maybe<User> getUserInformation() {
        return usersService.getUserInformation()
                .subscribeOn(ioScheduler);
    }
}
