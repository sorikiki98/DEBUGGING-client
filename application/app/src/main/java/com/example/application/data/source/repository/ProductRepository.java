package com.example.application.data.source.repository;

import com.example.application.data.Product;

import java.util.List;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface ProductRepository {
    Flowable<List<Product>> getProducts(boolean isFirstLoad);

    Completable addProductInterest(int productId);

    Completable removeProductInterest(int productId);

    List<Product> filterProducts(String keyword);

    boolean isProductInterested(int productId);

    void refreshCache(List<Product> products);

    void refreshLocalDataSource(List<Product> products);
}
