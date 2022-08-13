package com.example.application.register;

import android.widget.Toast;

import com.example.application.data.RegistrationForm;
import com.example.application.data.UserAuthentication;
import com.example.application.data.source.repository.UserRepository;

import java.net.SocketTimeoutException;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.HttpException;

public class RegisterPresenter implements RegisterContract.Presenter {
    private final UserRepository userRepository;
    private final RegisterContract.View view;
    private final Scheduler mainScheduler;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RegisterPresenter(UserRepository userRepository, RegisterContract.View view, Scheduler scheduler) {
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
    public void register(RegistrationForm form) {
        userRepository.signup(form)
                .observeOn(mainScheduler)
                .subscribe(new MaybeObserver<UserAuthentication>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull UserAuthentication userAuthentication) {
                        view.navigate(0);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            view.toastErrorMessage("서버와 연결하는 데 실패했습니다.(Connection failed)");
                        } else if (e instanceof HttpException) {
                            if (((HttpException) e).code() == 409) {
                                view.toastErrorMessage("이미 존재하는 유저명입니다.");
                            } else {
                                view.toastErrorMessage("서버 내부 오류(Internal server error)");
                            }
                        } else view.toastErrorMessage("알 수 없는 오류");

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
