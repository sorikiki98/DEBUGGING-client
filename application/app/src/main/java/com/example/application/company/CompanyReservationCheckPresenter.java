package com.example.application.company;

import android.util.Log;

import com.example.application.data.Company;
import com.example.application.data.ReservationForm;
import com.example.application.data.User;
import com.example.application.data.source.repository.CompanyRepository;
import com.example.application.data.source.repository.UserRepository;

import org.reactivestreams.Subscription;

import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class CompanyReservationCheckPresenter implements CompanyReservationCheckContract.Presenter {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final CompanyReservationCheckContract.View view;
    private final Scheduler mainScheduler;

    private int companyId;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Subscription subscription;

    @Inject
    public CompanyReservationCheckPresenter(CompanyRepository companyRepository, UserRepository userRepository, CompanyReservationCheckContract.View view, Scheduler scheduler) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.view = view;
        this.mainScheduler = scheduler;
    }

    @Override
    public void subscribe() {
        getReservationCheckInformation();
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
        subscription.cancel();
    }

    @Override
    public void getReservationCheckInformation() {
        getUserInformation()
                .observeOn(mainScheduler)
                .flatMap((User user) -> {
                    ReservationForm form = getReservationForm();
                    view.showReservationInformation(form);
                    view.showUserInformation(user);
                    return getCompanyInformation();
                })
                .subscribe(new FlowableSubscriber<Optional<Company>>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                        s.request(Long.MAX_VALUE);
                        subscription = s;
                    }

                    @Override
                    public void onNext(Optional<Company> company) {
                        Log.d("CompanyReservationCheckPresenter", company.get().toString());
                        view.showCompanyInformation(company.get());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("CompanyReservationCheckPresenter", t.getMessage());
                        view.showLoadingErrorMessage("정보를 불러올 수 없습니다.");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("CompanyReservationCheckPresenter", "completed");
                    }
                });

    }

    @Override
    public void reserve(int companyId) {
        companyRepository.reserveCompany(companyId)
                .observeOn(mainScheduler)
                .subscribe(new MaybeObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        view.navigate(integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.toastReservationErrorMessage("예약에 실패하였습니다.");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private ReservationForm getReservationForm() {
        return companyRepository.getReservationForm();
    }

    private Flowable<User> getUserInformation() {
        return userRepository.loadUserInformation(true)
                .toFlowable();
    }

    private Flowable<Optional<Company>> getCompanyInformation() {
        return companyRepository.getCompanyWithId(companyId);
    }

    @Override
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
