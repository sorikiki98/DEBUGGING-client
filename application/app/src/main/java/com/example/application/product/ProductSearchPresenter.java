package com.example.application.product;

import com.example.application.data.source.repository.ProductRepository;

public class ProductSearchPresenter implements ProductSearchContract.Presenter {
    private final ProductRepository productRepository;
    private final ProductSearchContract.View view;

    public ProductSearchPresenter(ProductRepository productRepository, ProductSearchContract.View view) {
        this.productRepository = productRepository;
        this.view = view;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void filterProducts(String keyword) {
        view.showFilteredProducts(productRepository.filterProducts(keyword));
    }
}
