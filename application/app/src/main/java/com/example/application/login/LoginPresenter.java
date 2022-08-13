package com.example.application.login;

import com.example.application.data.UserAuthentication;
import com.example.application.data.UserLogIn;
import com.example.application.data.source.repository.UserRepository;

import java.net.SocketTimeoutException;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.UndeliverableException;
import retrofit2.HttpException;

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
    public void subscribe() {
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

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
                        if (e instanceof SocketTimeoutException) {
                            view.processLoginFail("서버와 연결하는 데 실패했습니다.(Connection failed)");
                        }
                        else if (e instanceof HttpException) {
                            if (((HttpException) e).code() == 401) {
                                view.processLoginFail("아이디 혹은 비밀번호를 다시 확인해주세요.");
                            } else {
                                view.processLoginFail("서버 내부 오류(Internal server error)");
                            }
                        }
                        else view.processLoginFail("알 수 없는 오류(Unknown error)");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
