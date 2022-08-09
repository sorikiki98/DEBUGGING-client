package com.example.application.register;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.UserRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class RegisterModule {
    @Binds
    abstract RegisterContract.View bindRegisterFragment(RegisterFragment registerFragment);

    @Provides
    static RegisterContract.Presenter provideRegisterPresenter(UserRepository userRepository, RegisterContract.View view, SchedulersFacade scheduler) {
        return new RegisterPresenter(userRepository, view, scheduler.ui());
    }
}
