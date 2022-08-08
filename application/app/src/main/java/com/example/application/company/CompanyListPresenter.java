package com.example.application.company;

import android.util.Log;

import com.example.application.data.Company;
import com.example.application.data.source.repository.CompanyRepository;

import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class CompanyListPresenter implements CompanyListContract.Presenter {
    private final CompanyRepository companyRepository;
    private final CompanyListContract.View view;
    private final Scheduler mainScheduler;

    private Subscription subscription;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public CompanyListPresenter(CompanyRepository companyRepository, CompanyListContract.View view, Scheduler scheduler) {
        this.companyRepository = companyRepository;
        this.view = view;
        this.mainScheduler = scheduler;
    }

    @Override
    public void subscribe() {
        loadCompanies();
    }

    @Override
    public void unsubscribe() {
        subscription.cancel();
        compositeDisposable.clear();
    }

    @Override
    public void loadCompanies() {
        companyRepository.getCompanies()
                .observeOn(mainScheduler)
                .subscribe(getCompanyListFlowableSubscriber());
    }

    @Override
    public void toggleCompanyInterest(int companyId) {
        boolean isCompanyInterested = companyRepository.isCompanyInterested(companyId);
        if (isCompanyInterested) removeCompanyInterest(companyId);
        else addCompanyInterest(companyId);
    }

    private void addCompanyInterest(int companyId) {
        companyRepository.addCompanyInterest(companyId)
                .subscribe(getCompanyInterestCompletableObserver());
    }

    private void removeCompanyInterest(int companyId) {
        companyRepository.removeCompanyInterest(companyId)
                .subscribe(getCompanyInterestCompletableObserver());
    }

    private CompletableObserver getCompanyInterestCompletableObserver() {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                compositeDisposable.add(d);
            }

            @Override
            public void onComplete() {
                subscribe();
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        };
    }
    private FlowableSubscriber<List<Company>> getCompanyListFlowableSubscriber() {
        return new FlowableSubscriber<List<Company>>() {
            @Override
            public void onSubscribe(@NonNull Subscription s) {
                s.request(Long.MAX_VALUE);
                subscription = s;
            }

            @Override
            public void onNext(List<Company> companies) {
                view.showCompanies(companies);
            }

            @Override
            public void onError(Throwable t) {
                view.showErrorMessage("목록을 불러올 수 없습니다.");
            }

            @Override
            public void onComplete() {

            }
        };
    }
}
