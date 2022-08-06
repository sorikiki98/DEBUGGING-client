package com.example.application.data.source.remote;

import com.example.application.PreferencesManager;
import com.example.application.data.Product;
import com.example.application.data.source.ProductDataSource;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;

public class ProductRemoteDataSource implements ProductDataSource {
    private final ProductService productService;
    private final Scheduler ioScheduler;
    private final PreferencesManager preferencesManager;

    @Inject
    public ProductRemoteDataSource(ProductService productService, Scheduler scheduler, PreferencesManager preferencesManager) {
        this.productService = productService;
        this.ioScheduler = scheduler;
        this.preferencesManager = preferencesManager;
    }

    @Override
    public Flowable<List<Product>> getProducts() {
        return productService.getProducts(getAuthToken())
                .subscribeOn(ioScheduler);
    }

    @Override
    public Flowable<Optional<Product>> getProductWithId(int productId) {
        return productService.getProduct(productId, getAuthToken())
                .subscribeOn(ioScheduler);
    }

    @Override
    public Completable addProductInterest(int productId) {
        return productService.addProductInterest(productId, getAuthToken())
                .subscribeOn(ioScheduler);
    }

    @Override
    public Completable removeProductInterest(int productId) {
        return productService.removeProductInterest(productId, getAuthToken())
                .subscribeOn(ioScheduler);
    }

    @Override
    public void insertProducts(List<Product> products) {

    }

    private String getAuthToken() {
        return "Bearer " + preferencesManager.fetchAuthToken();
    }
}
