package com.example.application.mypage;

import com.example.application.product.ProductItemFragment;
import com.example.application.product.ProductItemModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MyPageFragmentsModule {
    @ContributesAndroidInjector(modules = MyPageMainModule.class)
    abstract MyPageMainFragment contributeMyPageMainFragment();

    @ContributesAndroidInjector(modules = MyPageCompanyDetailListModule.class)
    abstract MyPageCompanyDetailListFragment contributeMyPageCompanyDetailListFragment();

    @ContributesAndroidInjector(modules = MyPageCompanyDetailItemModule.class)
    abstract MyPageCompanyDetailItemFragment contributeMyPageCompanyDetailItemFragment();

    @ContributesAndroidInjector(modules = MyPageProductInterestListModule.class)
    abstract MyPageProductInterestListFragment contributeMyPageProductInterestListFragment();

    @ContributesAndroidInjector(modules = ProductItemModule.class)
    abstract ProductItemFragment contributeMyPageProductItemFragment();
}
