package com.example.application.product;

import com.example.application.SchedulersFacade;
import com.example.application.data.source.repository.ProductRepository;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ProductListModule {
    @Binds
    abstract ProductListContract.View bindProductListFragment(ProductListFragment productListFragment);

    @Provides
    static ProductListContract.Presenter provideProductListPresenter(ProductRepository productRepository, ProductListContract.View view, SchedulersFacade scheduler) {
        return new ProductListPresenter(productRepository, view, scheduler.ui());
    }
}
