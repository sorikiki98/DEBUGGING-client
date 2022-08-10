package com.example.application.data.source.repository;

import android.util.Log;

import com.example.application.data.Bug;
import com.example.application.data.Company;
import com.example.application.data.Product;
import com.example.application.data.ReservationForm;
import com.example.application.data.source.local.ProductLocalDataSource;
import com.example.application.data.source.remote.ProductRemoteDataSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class ProductRepositoryImpl implements ProductRepository {
    private final ProductRemoteDataSource productRemoteDataSource;
    private final ProductLocalDataSource productLocalDataSource;

    private HashMap<Integer, Product> cachedProducts = new LinkedHashMap<>();

    private boolean isCacheDirty = false;

    private boolean isFirstLoad = true;

    @Inject
    public ProductRepositoryImpl(ProductRemoteDataSource productRemoteDataSource, ProductLocalDataSource productLocalDataSource) {
        this.productRemoteDataSource = productRemoteDataSource;
        this.productLocalDataSource = productLocalDataSource;
    }

    @Override
    public Flowable<List<Product>> getProducts() {
        if (!isCacheDirty && !cachedProducts.isEmpty()) {
            Log.d("ProductRepositoryImpl", "cache");
            return Flowable.just(new ArrayList<>(cachedProducts.values()));
        }

        if (isCacheDirty && isFirstLoad) {
            return getProductsFromRemoteDataSource();
        }

        return productLocalDataSource.getProducts()
                .flatMap((List<Product> products) -> {
                    if (products.isEmpty()) {
                        Log.d("ProductRepositoryImpl", "remote");
                        return getProductsFromRemoteDataSource();
                    }
                    Log.d("ProductRepositoryImpl", "local");
                    refreshCache(products);
                    isCacheDirty = false;
                    return Flowable.just(products);
                });
    }


    @Override
    public Completable addProductInterest(int productId) {
        isCacheDirty = true;
        isFirstLoad = false;
        return productRemoteDataSource.addProductInterest(productId)
                .andThen(productLocalDataSource.addProductInterest(productId));
    }

    @Override
    public Completable removeProductInterest(int productId) {
        isCacheDirty = true;
        isFirstLoad = false;
        return productRemoteDataSource.removeProductInterest(productId)
                .andThen(productLocalDataSource.removeProductInterest(productId));
    }

    @Override
    public boolean isProductInterested(int productId) {
        if (cachedProducts != null && cachedProducts.get(productId) != null) {
            int isProductInterested = Objects.requireNonNull(cachedProducts.get(productId)).isProductInterested;
            return isProductInterested == 1;
        }
        return false;
    }

    @Override
    public void refreshProducts() {
        isCacheDirty = true;
        isFirstLoad = true;
        getProducts();
    }

    @Override
    public void refreshCache(List<Product> products) {
        cachedProducts = new LinkedHashMap<>();
        for (Product product : products) {
            cachedProducts.put(product.id, product);
        }
    }

    @Override
    public void refreshLocalDataSource(List<Product> products) {
        productLocalDataSource.insertProducts(products);
    }

    private Flowable<List<Product>> getProductsFromRemoteDataSource() {
        return productRemoteDataSource.getProducts()
                .flatMap((List<Product> products) -> {
                    refreshLocalDataSource(products);
                    refreshCache(products);
                    isCacheDirty = false;
                    isFirstLoad = false;
                    return Flowable.just(products);
                });
    }
}