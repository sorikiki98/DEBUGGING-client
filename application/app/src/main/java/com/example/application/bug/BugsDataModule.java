package com.example.application.bug;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.BugsDataSource;
import com.example.application.data.source.local.BugsDao;
import com.example.application.data.source.local.BugsLocalDataSource;
import com.example.application.data.source.remote.BugsRemoteDataSource;
import com.example.application.data.source.remote.BugsService;
import com.example.application.data.source.repository.BugsRepository;
import com.example.application.data.source.repository.BugsRepositoryImpl;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.core.Scheduler;

@Module
public abstract class BugsDataModule {
    @Provides
    static BugsRemoteDataSource providesBugsRemoteDataSource(BugsService bugsService, SchedulersFacade scheduler) {
        return new BugsRemoteDataSource(bugsService, scheduler.io());
    }

    @Provides
    static BugsLocalDataSource providesBugsLocalDataSource(BugsDao bugsDao, SchedulersFacade scheduler) {
        return new BugsLocalDataSource(bugsDao, scheduler.io());
    }

    @Binds
    abstract BugsRepository bindBugsRepository(BugsRepositoryImpl repository);
}
