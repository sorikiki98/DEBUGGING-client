package com.example.application.mypage;

import com.example.application.data.User;
import com.example.application.data.source.repository.UserRepository;

import io.reactivex.rxjava3.annotations.NonNull;
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
        loadUserInfo();
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadUserInfo() {
        userRepository.loadUserInformation()
                .observeOn(mainScheduler)
                .subscribe(new MaybeObserver<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull User user) {
                        view.showUserInfo(user);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showErrorMessage();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
