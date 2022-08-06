package com.example.application.data.source;

import com.example.application.data.Company;
import com.example.application.data.Product;

import java.util.List;
import java.util.Optional;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface ProductDataSource {
    public Flowable<List<Product>> getProducts();

    public Flowable<Optional<Product>> getProductWithId(int productId);

    public Completable addProductInterest(int productId);

    public Completable removeProductInterest(int productId);

    void insertProducts(List<Product> products);
}
