package com.example.application.product;

import com.example.application.company.CompanyItemFragment;
import com.example.application.company.CompanyItemModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ProductFragmentsModule {
    @ContributesAndroidInjector(modules = ProductListModule.class)
    abstract ProductListFragment contributeProductListFragment();

    @ContributesAndroidInjector(modules = ProductItemModule.class)
    abstract ProductItemFragment contributeProductItemFragment();
}
