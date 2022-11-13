package com.example.application.di;

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
    @Singleton
    @Binds
    abstract BugRepository bindBugsRepository(BugRepositoryImpl repository);

    @Singleton
    @Binds
    abstract UserRepository bindUserRepository(UserRepositoryImpl userRepository);

    @Singleton
    @Binds
    abstract CompanyRepository bindCompanyRepository(CompanyRepositoryImpl companyRepository);

    @Singleton
    @Binds
    abstract ProductRepository bindProductRepository(ProductRepositoryImpl productRepository);
}

