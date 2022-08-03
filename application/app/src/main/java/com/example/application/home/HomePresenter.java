package com.example.application.home;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.application.data.source.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;

public class HomePresenter implements HomeContract.Presenter {
    private final UserRepository userRepository;
    private final HomeContract.View view;
    private final Scheduler mainScheduler;
    private final Scheduler ioScheduler;
    private final Context context;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public HomePresenter(UserRepository userRepository, HomeContract.View view, Scheduler mainScheduler, Scheduler ioScheduler, Context context) {
        this.userRepository = userRepository;
        this.view = view;
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.context = context;
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void setUserName() {
        String userName = userRepository.getUserName();
        view.showUserName(userName);
    }

    @Override
    public void setAccumulatedNumberOfUsages() {
        userRepository.getAccumulatedNumOfUsages()
                .observeOn(mainScheduler)
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        view.showAccumulatedNumberOfUsages(integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showAccumulatedNumberOfUsages(0);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void logout() {
        userRepository.logout()
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onComplete() {
                        view.goBackToHomeScreen();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, "로그아웃 실패", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void signOut() {
        userRepository.delete()
                .observeOn(mainScheduler)
                .subscribe(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        view.goBackToHomeScreen();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(context, "회원탈퇴 실패", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
