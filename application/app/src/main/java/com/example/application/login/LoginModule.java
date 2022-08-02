package com.example.application.login;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.remote.UserRemoteDataSource;
import com.example.application.data.source.remote.UsersService;
import com.example.application.data.source.repository.UserRepository;
import com.example.application.data.source.repository.UserRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class LoginModule {
    @Provides
    static UserRemoteDataSource provideUserRemoteDataSource(UsersService usersService, SchedulersFacade scheduler) {
        return new UserRemoteDataSource(usersService, scheduler.io());
    }

    @Binds
    abstract UserRepository bindUserRepository(UserRepositoryImpl userRepository);

    @Binds
    abstract LoginContract.View bindLoginActivity(LoginActivity loginActivity);

    @Provides
    static LoginContract.Presenter provideLoginPresenter(UserRepository userRepository, LoginContract.View view, SchedulersFacade scheduler) {
        return new LoginPresenter(userRepository, view, scheduler.ui());
    }

}
