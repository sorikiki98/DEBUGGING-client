package com.example.application.mypage;

import android.util.Log;

import com.example.application.data.User;
import com.example.application.data.source.repository.UserRepository;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MyPageMainPresenter implements MyPageMainContract.Presenter {
    private final UserRepository userRepository;
    private final MyPageMainContract.View view;
    private final Scheduler mainScheduler;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MyPageMainPresenter(
            UserRepository userRepository,
            MyPageMainContract.View view,
            Scheduler scheduler
    ) {
        this.userRepository = userRepository;
        this.view = view;
        this.mainScheduler = scheduler;
    }

    @Override
    public void subscribe() {
        getUserInfo();
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void getUserInfo() {
        loadUserInfo(true);
    }

    @Override
    public void refreshUserInfo() {
        loadUserInfo(false);
    }

    private void loadUserInfo(boolean isFirstLoad) {
        userRepository.loadUserInformation(isFirstLoad)
                .observeOn(mainScheduler)
                .subscribe(new MaybeObserver<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull User user) {
                        view.showUserInfo(user);
                        view.undoRefreshLoading();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showErrorMessage("정보를 불러올 수 없습니다.");
                        view.undoRefreshLoading();
                    }

                    @Override
                    public void onComplete() {
                        view.undoRefreshLoading();
                    }
                });
    }
}

