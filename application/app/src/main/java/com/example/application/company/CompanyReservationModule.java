package com.example.application.company;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.CompanyRepository;
import com.example.application.data.source.repository.UserRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CompanyReservationModule {
    @Binds
    abstract CompanyReservationContract.View bindCompanyReservationFragment(CompanyReservationFragment companyReservationFragment);

    @Provides
    static CompanyReservationContract.Presenter provideCompanyReservationPresenter(CompanyRepository companyRepository, UserRepository userRepository, CompanyReservationContract.View view, SchedulersFacade scheduler) {
        return new CompanyReservationPresenter(companyRepository, userRepository, view, scheduler.ui());
    }
}
