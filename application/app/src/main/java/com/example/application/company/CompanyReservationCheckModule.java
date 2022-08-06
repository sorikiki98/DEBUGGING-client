package com.example.application.company;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.CompanyRepository;
import com.example.application.data.source.repository.UserRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CompanyReservationCheckModule {
    @Binds
    abstract CompanyReservationCheckContract.View bindCompanyReservationCheckFragment(CompanyReservationCheckFragment fragment);

    @Provides
    static CompanyReservationCheckContract.Presenter provideCompanyReservationCheckPresenter(CompanyRepository companyRepository, UserRepository userRepository, CompanyReservationCheckContract.View view, SchedulersFacade scheduler) {
        return new CompanyReservationCheckPresenter(companyRepository, userRepository, view, scheduler.ui());
    }
}
