package com.example.application.register;

import android.widget.Toast;

import com.example.application.data.RegistrationForm;
import com.example.application.data.UserAuthentication;
import com.example.application.data.source.repository.UserRepository;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

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
                        view.toastErrorMessage("회원가입에 실패하였습니다.");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
