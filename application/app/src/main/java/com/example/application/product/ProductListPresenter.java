package com.example.application.product;

import android.util.Log;

import com.example.application.data.Company;
import com.example.application.data.Product;
import com.example.application.data.source.repository.ProductRepository;
import com.example.application.product.ProductListContract;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class ProductListPresenter implements ProductListContract.Presenter {
    private final ProductRepository productRepository;
    private final ProductListContract.View view;
    private final Scheduler mainScheduler;

    private Subscription subscription;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ProductListPresenter(ProductRepository repository, ProductListContract.View view, Scheduler scheduler) {
        this.productRepository = repository;
        this.view = view;
        this.mainScheduler = scheduler;
    }

    @Override
    public void subscribe() {
        loadProducts();
    }

    @Override
    public void unsubscribe() {
        subscription.cancel();
        compositeDisposable.clear();
    }

    @Override
    public void loadProducts() {
        productRepository.getProducts()
                .observeOn(mainScheduler)
                .subscribe(getProductListFlowableSubscriber());
    }

    @Override
    public void toggleProductInterest(int productId) {
        boolean isProductInterested = productRepository.isProductInterested(productId);
        if (isProductInterested) removeProductInterest(productId);
        else addProductInterest(productId);
    }

    private void addProductInterest(int productId) {
        productRepository.addProductInterest(productId)
                .subscribe(getProductInterestCompletableObserver());
    }

    private void removeProductInterest(int productId) {
        productRepository.removeProductInterest(productId)
                .subscribe(getProductInterestCompletableObserver());
    }

    private CompletableObserver getProductInterestCompletableObserver() {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };
    }

    private FlowableSubscriber<List<Product>> getProductListFlowableSubscriber() {
        return new FlowableSubscriber<List<Product>>() {
            @Override
            public void onSubscribe(@NonNull Subscription s) {
                s.request(Long.MAX_VALUE);
                subscription = s;
            }

            @Override
            public void onNext(List<Product> products) {
                view.showProducts(products);
            }

            @Override
            public void onError(Throwable t) {
                view.showErrorMessage("목록을 불러올 수 없습니다.");
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
