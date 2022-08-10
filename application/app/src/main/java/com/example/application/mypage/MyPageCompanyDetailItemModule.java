package com.example.application.mypage;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.CompanyRepository;
import com.example.application.data.source.repository.UserRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class MyPageCompanyDetailItemModule {
    @Binds
    abstract MyPageCompanyDetailItemContract.View bindMyPageCompanyDetailItemFragment(MyPageCompanyDetailItemFragment myPageCompanyDetailItemFragment);

    @Provides
    static MyPageCompanyDetailItemContract.Presenter provideMyPageCompanyDetailItemPresenter(CompanyRepository companyRepository, MyPageCompanyDetailItemContract.View view, SchedulersFacade scheduler) {
        return new MyPageCompanyDetailItemPresenter(companyRepository, view, scheduler.ui());
    }

}
