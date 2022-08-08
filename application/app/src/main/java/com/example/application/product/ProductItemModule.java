package com.example.application.product;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.ProductRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ProductItemModule {
    @Binds
    abstract ProductItemContract.View bindProductItemFragment(ProductItemFragment productItemFragment);

    @Provides
    static ProductItemContract.Presenter provideProductItemPresenter(ProductRepository productRepository, ProductItemContract.View view, SchedulersFacade scheduler) {
        return new ProductItemPresenter(productRepository, view, scheduler.ui());
    }
}
