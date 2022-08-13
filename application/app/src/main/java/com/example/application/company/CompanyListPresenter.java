package com.example.application.company;

import com.example.application.data.Company;
import com.example.application.data.source.repository.CompanyRepository;

import org.reactivestreams.Subscription;

import java.net.SocketTimeoutException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.HttpException;

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
        getCompanies();
    }

    @Override
    public void unsubscribe() {
        subscription.cancel();
        compositeDisposable.clear();
    }

    @Override
    public void getCompanies() {
        loadCompanies(true);
    }

    @Override
    public void refreshCompanies() {
        loadCompanies(false);
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
                if (e instanceof SocketTimeoutException) {
                    view.showErrorMessage("서버와 연결하는 데 실패했습니다.(Connection failed)");
                }
                else if (e instanceof HttpException) {
                    if (((HttpException) e).code() == 404) {
                        view.showErrorMessage("찜하기 해제 실패");
                    } else {
                        view.showErrorMessage("찜하기 실패");
                    }
                }
                else view.showErrorMessage("알 수 없는 오류");
            }
        };
    }

    private void loadCompanies(boolean isFirstLoad) {
        companyRepository.getCompanies(isFirstLoad)
                .observeOn(mainScheduler)
                .subscribe(new FlowableSubscriber<List<Company>>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                        s.request(Long.MAX_VALUE);
                        subscription = s;
                    }

                    @Override
                    public void onNext(List<Company> companies) {
                        view.showCompanies(companies);
                        view.undoRefreshLoading();
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.showErrorMessage("목록을 불러올 수 없습니다.");
                        view.undoRefreshLoading();
                    }

                    @Override
                    public void onComplete() {
                        view.undoRefreshLoading();
                    }
                });
    }
}
