package com.example.application.di;

import com.example.application.PreferencesManager;
import com.example.application.SchedulersFacade;
import com.example.application.data.source.local.BugDao;
import com.example.application.data.source.local.BugLocalDataSource;
import com.example.application.data.source.local.CompanyDao;
import com.example.application.data.source.local.CompanyLocalDataSource;
import com.example.application.data.source.local.ProductDao;
import com.example.application.data.source.local.ProductLocalDataSource;
import com.example.application.data.source.remote.BugRemoteDataSource;
import com.example.application.data.source.remote.BugService;
import com.example.application.data.source.remote.CompanyRemoteDataSource;
import com.example.application.data.source.remote.CompanyService;
import com.example.application.data.source.remote.ProductRemoteDataSource;
import com.example.application.data.source.remote.ProductService;
import com.example.application.data.source.remote.UserRemoteDataSource;
import com.example.application.data.source.remote.UserService;
import com.example.application.data.source.repository.BugRepository;
import com.example.application.data.source.repository.BugRepositoryImpl;
import com.example.application.data.source.repository.CompanyRepository;
import com.example.application.data.source.repository.CompanyRepositoryImpl;
import com.example.application.data.source.repository.ProductRepository;
import com.example.application.data.source.repository.ProductRepositoryImpl;
import com.example.application.data.source.repository.UserRepository;
import com.example.application.data.source.repository.UserRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class DataModule {
    // bug
    @Singleton
    @Provides
    static BugRemoteDataSource providesBugsRemoteDataSource(BugService bugService, SchedulersFacade scheduler, PreferencesManager preferencesManager) {
        return new BugRemoteDataSource(bugService, scheduler.io(), preferencesManager);
    }

    @Singleton
    @Provides
    static BugLocalDataSource providesBugsLocalDataSource(BugDao bugDao, SchedulersFacade scheduler) {
        return new BugLocalDataSource(bugDao, scheduler.io());
    }

    @Singleton
    @Binds
    abstract BugRepository bindBugsRepository(BugRepositoryImpl repository);

    // user
    @Singleton
    @Provides
    static UserRemoteDataSource provideUserRemoteDataSource(UserService userService, SchedulersFacade scheduler, PreferencesManager preferencesManager) {
        return new UserRemoteDataSource(userService, scheduler.io(), preferencesManager);
    }

    @Singleton
    @Binds
    abstract UserRepository bindUserRepository(UserRepositoryImpl userRepository);

    // company
    @Singleton
    @Provides
    static CompanyRemoteDataSource provideCompanyRemoteDataSource(CompanyService companyService, SchedulersFacade scheduler, PreferencesManager preferencesManager) {
        return new CompanyRemoteDataSource(companyService, scheduler.io(), preferencesManager);
    }

    @Singleton
    @Provides
    static CompanyLocalDataSource provideCompanyLocalDataSource(CompanyDao companyDao, SchedulersFacade scheduler) {
        return new CompanyLocalDataSource(companyDao, scheduler.io());
    }

    @Singleton
    @Binds
    abstract CompanyRepository bindCompanyRepository(CompanyRepositoryImpl companyRepository);

    // product
    @Singleton
    @Provides
    static ProductRemoteDataSource provideProductRemoteDataSource(ProductService productService, SchedulersFacade scheduler, PreferencesManager preferencesManager) {
        return new ProductRemoteDataSource(productService, scheduler.io(), preferencesManager);
    }

    @Singleton
    @Provides
    static ProductLocalDataSource provideProductLocalDataSource(ProductDao productDao,SchedulersFacade scheduler) {
        return new ProductLocalDataSource(productDao, scheduler.io());
    }

    @Singleton
    @Binds
    abstract ProductRepository bindProductRepository(ProductRepositoryImpl productRepository);
}

