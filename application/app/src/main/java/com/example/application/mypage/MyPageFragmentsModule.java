package com.example.application.mypage;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MyPageFragmentsModule {
    @ContributesAndroidInjector(modules = MyPageMainModule.class)
    abstract MyPageMainFragment contributeMyPageMainFragment();

    @ContributesAndroidInjector
    abstract MyPageCompanyDetailListFragment contributeMyPageCompanyDetailListFragment();

    @ContributesAndroidInjector
    abstract MyPageCompanyDetailItemFragment contributeMyPageCompanyDetailItemFragment();

    @ContributesAndroidInjector
    abstract MyPageProductInterestListFragment contributeMyPageProductInterestListFragment();
}
