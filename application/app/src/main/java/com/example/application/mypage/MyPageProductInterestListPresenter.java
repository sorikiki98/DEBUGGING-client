package com.example.application.mypage;

import com.example.application.data.MyProduct;
import com.example.application.data.source.repository.UserRepository;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MyPageProductInterestListPresenter implements MyPageProductInterestListContract.Presenter {
    private final UserRepository userRepository;
    private final MyPageProductInterestListContract.View view;
    private final Scheduler mainScheduler;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MyPageProductInterestListPresenter(
            UserRepository userRepository,
            MyPageProductInterestListContract.View view,
            Scheduler scheduler
    ) {
        this.userRepository = userRepository;
        this.view = view;
        this.mainScheduler = scheduler;
    }

    @Override
    public void subscribe() {
        getMyPageProductInterest();
        getUserName();
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void getMyPageProductInterest() {
       loadMyPageProductInterest(true);
    }

    @Override
    public void refreshMyPageProductInterest() {
        loadMyPageProductInterest(false);
    }

    @Override
    public void getUserName() {
        view.showUserName(userRepository.getUserName());
    }

    private void loadMyPageProductInterest(boolean isFirstLoad) {
        userRepository.getMyProductList(isFirstLoad)
                .observeOn(mainScheduler)
                .subscribe(new MaybeObserver<List<MyProduct>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<MyProduct> myProductList) {
                        view.showMyPageProductInterest(myProductList);
                        view.undoRefreshLoading();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showErrorMessage("목록을 불러올 수 없습니다.");
                        view.undoRefreshLoading();
                    }

                    @Override
                    public void onComplete() {
                        view.undoRefreshLoading();
                    }
                });
    }
}
