package com.example.application.mypage;

import com.example.application.data.MyReservation;
import com.example.application.data.source.repository.UserRepository;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MyPageCompanyDetailListPresenter implements MyPageCompanyDetailListContract.Presenter {
    private final UserRepository userRepository;
    private final MyPageCompanyDetailListContract.View view;
    private final Scheduler mainScheduler;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MyPageCompanyDetailListPresenter(
            UserRepository userRepository,
            MyPageCompanyDetailListContract.View view,
            Scheduler scheduler
    ) {
        this.userRepository = userRepository;
        this.view = view;
        this.mainScheduler = scheduler;
    }

    @Override
    public void subscribe() {
        loadReservedCompanies();
        loadUserName();
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void loadReservedCompanies() {
        userRepository.getMyReservationList()
                .observeOn(mainScheduler)
                .subscribe(new MaybeObserver<List<MyReservation>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<MyReservation> myReservations) {
                        view.showReservedCompanies(myReservations);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showErrorMessage("목록을 불러올 수 없습니다.");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadUserName() {
        view.showUserName(userRepository.getUserName());
    }
}
