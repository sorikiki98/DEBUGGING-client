package com.example.application.mypage;

import com.example.application.data.Reservation;
import com.example.application.data.source.repository.CompanyRepository;
import com.example.application.data.source.repository.UserRepository;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MyPageCompanyDetailItemPresenter implements MyPageCompanyDetailItemContract.Presenter {
    private final CompanyRepository companyRepository;
    private final MyPageCompanyDetailItemContract.View view;
    private final Scheduler mainScheduler;
    private int reservationId;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MyPageCompanyDetailItemPresenter(
            CompanyRepository companyRepository,
           MyPageCompanyDetailItemContract.View view,
           Scheduler scheduler
    ) {
        this.companyRepository = companyRepository;
        this.view = view;
        this.mainScheduler = scheduler;
    }

    @Override
    public void subscribe() {
        loadReservationInformation();
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void loadReservationInformation() {
        companyRepository.getReservationInformation(reservationId)
                .observeOn(mainScheduler)
                .subscribe(new MaybeObserver<Reservation>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Reservation reservation) {
                        view.showReservationInformation(reservation);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showErrorMessage("정보를 불러올 수 없습니다.");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
}
