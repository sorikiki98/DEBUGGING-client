package com.example.application.login;

import android.transition.Scene;
import android.util.Log;

import com.example.application.SchedulersFacade;
import com.example.application.data.User;
import com.example.application.data.UserAuthentication;
import com.example.application.data.source.remote.UserLogIn;
import com.example.application.data.source.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class LoginPresenter implements LoginContract.Presenter {
    private final UserRepository userRepository;
    private final LoginContract.View view;
    private final Scheduler mainScheduler;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public LoginPresenter(UserRepository userRepository, LoginContract.View view, Scheduler scheduler) {
        this.userRepository = userRepository;
        this.view = view;
        this.mainScheduler = scheduler;
    }

    @Override
    public void subscribe() {}

    @Override
    public void unsubscribe() { compositeDisposable.clear();}

    // todo 로그인 실패시 4xx 인지, 5xx 인지
    @Override
    public void login(UserLogIn userInput) {
       userRepository.login(userInput)
                .observeOn(mainScheduler)
                .subscribe(new MaybeObserver<UserAuthentication>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull UserAuthentication userAuthentication) {
                        view.processLoginSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.processLoginFail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
