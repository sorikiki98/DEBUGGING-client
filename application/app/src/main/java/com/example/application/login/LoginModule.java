package com.example.application.login;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.UserRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class LoginModule {
    @Binds
    abstract LoginContract.View bindLoginActivity(LoginActivity loginActivity);

    @Provides
    static LoginContract.Presenter provideLoginPresenter(UserRepository userRepository, LoginContract.View view, SchedulersFacade scheduler) {
        return new LoginPresenter(userRepository, view, scheduler.ui());
    }

}
