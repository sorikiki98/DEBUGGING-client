package com.example.application.data.source.local;

import com.example.application.data.Product;
import com.example.application.data.source.ProductDataSource;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;

public class ProductLocalDataSource implements ProductDataSource {
    private final ProductDao productDao;
    private final Scheduler ioScheduler;

    @Inject
    public ProductLocalDataSource(ProductDao productDao, Scheduler scheduler) {
        this.productDao = productDao;
        this.ioScheduler = scheduler;
    }

    @Override
    public Flowable<List<Product>> getProducts() {
        return productDao.getProducts()
                .subscribeOn(ioScheduler);
    }

    @Override
    public Flowable<Optional<Product>> getProductWithId(int productId) {
        return productDao.getProduct(productId)
                .subscribeOn(ioScheduler);
    }

    @Override
    public Completable addProductInterest(int productId) {
        return productDao.addProductInterest(productId)
                .subscribeOn(ioScheduler);
    }

    @Override
    public Completable removeProductInterest(int productId) {
        return productDao.removeProductInterest(productId)
                .subscribeOn(ioScheduler);
    }

    @Override
    public void insertProducts(List<Product> products) {
        productDao.insertProducts(products);
    }
}
