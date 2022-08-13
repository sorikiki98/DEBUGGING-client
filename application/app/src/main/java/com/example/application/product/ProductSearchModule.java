package com.example.application.product;

import com.example.application.BasePresenter;
import com.example.application.BaseView;
import com.example.application.data.Product;
import com.example.application.data.source.repository.ProductRepository;

import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ProductSearchModule {
    @Binds
    abstract ProductSearchContract.View bindProductSearchFragment(ProductSearchFragment productSearchFragment);

    @Provides
    static ProductSearchContract.Presenter provideProductSearchPresenter(ProductRepository productRepository, ProductSearchContract.View view) {
        return new ProductSearchPresenter(productRepository, view);
    }
}
