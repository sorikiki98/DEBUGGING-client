package com.example.application.data.source.remote;

import com.example.application.PreferencesManager;
import com.example.application.SchedulersFacade;
import com.example.application.data.RegistrationForm;
import com.example.application.data.User;
import com.example.application.data.UserAuthentication;
import com.example.application.data.UserLogIn;
import com.example.application.data.source.UserDataSource;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Scheduler;

public class UserRemoteDataSource implements UserDataSource {
    private final UserService userService;

    private final Scheduler ioScheduler;

    @Inject
    public UserRemoteDataSource(UserService userService, SchedulersFacade schedulersFacade) {
        this.userService = userService;
        this.ioScheduler = schedulersFacade.io();
    }

    @Override
    public Maybe<UserAuthentication> login(UserLogIn userInput) {
        return userService.logIn(userInput)
                .subscribeOn(ioScheduler);
    }

    @Override
    public Maybe<UserAuthentication> signup(RegistrationForm registrationForm) {
        return userService.signup(registrationForm)
                .subscribeOn(ioScheduler);
    }

    @Override
    public Completable delete() {
        return userService.delete()
                .subscribeOn(ioScheduler);
    }

    @Override
    public Maybe<User> getUserInformation() {
        return userService.getUserInformation()
                .subscribeOn(ioScheduler);
    }
}
