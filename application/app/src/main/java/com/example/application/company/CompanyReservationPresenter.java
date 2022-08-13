package com.example.application.company;

import com.example.application.data.ReservationForm;
import com.example.application.data.User;
import com.example.application.data.source.repository.CompanyRepository;
import com.example.application.data.source.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class CompanyReservationPresenter implements CompanyReservationContract.Presenter {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final CompanyReservationContract.View view;
    private final Scheduler mainScheduler;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private int companyId;

    @Inject
    CompanyReservationPresenter(CompanyRepository companyRepository, UserRepository userRepository, CompanyReservationContract.View view, Scheduler scheduler) {
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.view = view;
        this.mainScheduler = scheduler;
    }


    @Override
    public void subscribe() {
        loadUserInformation();
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void storeReservationForm(ReservationForm reservationForm) {
        companyRepository.keepReservationForm(reservationForm);
        view.navigate(companyId);
    }

    @Override
    public void loadUserInformation() {
        userRepository.loadUserInformation(true)
                .observeOn(mainScheduler)
                .subscribe(new MaybeObserver<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull User user) {
                        view.showUserInformation(user);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
