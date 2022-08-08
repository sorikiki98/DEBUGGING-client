package com.example.application.data.source.repository;

import com.example.application.data.Company;
import com.example.application.data.Product;

import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

public interface ProductRepository {
    Flowable<List<Product>> getProducts();

    Completable addProductInterest(int productId);

    Completable removeProductInterest(int productId);

    boolean isProductInterested(int productId);

    void refreshProducts();

    void refreshCache(List<Product> products);

    void refreshLocalDataSource(List<Product> products);
}
