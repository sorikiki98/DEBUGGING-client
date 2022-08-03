package com.example.application.di;

import com.example.application.PreferencesManager;
import com.example.application.SchedulersFacade;
import com.example.application.data.source.local.BugsDao;
import com.example.application.data.source.local.BugsLocalDataSource;
import com.example.application.data.source.remote.BugsRemoteDataSource;
import com.example.application.data.source.remote.BugsService;
import com.example.application.data.source.remote.UserRemoteDataSource;
import com.example.application.data.source.remote.UsersService;
import com.example.application.data.source.repository.BugsRepository;
import com.example.application.data.source.repository.BugsRepositoryImpl;
import com.example.application.data.source.repository.UserRepository;
import com.example.application.data.source.repository.UserRepositoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class DataModule {
    // bug
    @Provides
    static BugsRemoteDataSource providesBugsRemoteDataSource(BugsService bugsService, SchedulersFacade scheduler, PreferencesManager preferencesManager) {
        return new BugsRemoteDataSource(bugsService, scheduler.io(), preferencesManager);
    }

    @Provides
    static BugsLocalDataSource providesBugsLocalDataSource(BugsDao bugsDao, SchedulersFacade scheduler) {
        return new BugsLocalDataSource(bugsDao, scheduler.io());
    }

    @Binds
    abstract BugsRepository bindBugsRepository(BugsRepositoryImpl repository);

    // user
    @Provides
    static UserRemoteDataSource provideUserRemoteDataSource(UsersService usersService, SchedulersFacade scheduler, PreferencesManager preferencesManager) {
        return new UserRemoteDataSource(usersService, scheduler.io(), preferencesManager);
    }

    @Binds
    abstract UserRepository bindUserRepository(UserRepositoryImpl userRepository);
}

