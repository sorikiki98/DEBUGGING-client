package com.example.application.product;

import com.example.application.data.Product;
import com.example.application.data.source.repository.ProductRepository;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class ProductItemPresenter implements ProductItemContract.Presenter {
    private final ProductRepository productRepository;
    private final ProductItemContract.View view;
    private final Scheduler mainScheduler;

    private int productId;

    private Subscription subscription;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ProductItemPresenter(
            ProductRepository productRepository,
            ProductItemContract.View view,
            Scheduler scheduler
    ) {
        this.productRepository = productRepository;
        this.view = view;
        this.mainScheduler = scheduler;
    }

    @Override
    public void subscribe() {
        loadProduct();
    }

    @Override
    public void unsubscribe() {
        subscription.cancel();
        compositeDisposable.clear();
    }

    @Override
    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public void loadProduct() {
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
                .observeOn(mainScheduler)
                .subscribe(getProductInterestCompletableObserver());
    }

    private void removeProductInterest(int productId) {
        productRepository.removeProductInterest(productId)
                .observeOn(mainScheduler)
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
                subscribe();
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
                for (Product product: products) {
                    if (product.id == productId) {
                        view.showProduct(product);
                    }
                }
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
