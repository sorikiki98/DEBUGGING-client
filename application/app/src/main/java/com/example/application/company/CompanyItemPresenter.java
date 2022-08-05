package com.example.application.company;

import com.example.application.data.Company;
import com.example.application.data.source.repository.CompanyRepository;

import org.reactivestreams.Subscription;

import java.util.Optional;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;

public class CompanyItemPresenter implements CompanyItemContract.Presenter {
    private final CompanyRepository companyRepository;
    private final CompanyItemContract.View view;
    private final Scheduler mainScheduler;

    private int companyId;

    private Subscription subscription;

    @Inject
    public CompanyItemPresenter(
            CompanyRepository companyRepository,
            CompanyItemContract.View view,
            Scheduler scheduler
    ) {
        this.companyRepository = companyRepository;
        this.view = view;
        this.mainScheduler = scheduler;
    }

    @Override
    public void subscribe() {
        loadCompany();
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public void loadCompany() {
        companyRepository.getCompanyWithId(companyId)
                .observeOn(mainScheduler)
                .subscribe(new FlowableSubscriber<Optional<Company>>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                        s.request(Long.MAX_VALUE);
                        subscription = s;
                    }

                    @Override
                    public void onNext(Optional<Company> company) {
                        if (company.isPresent()) {
                            view.showCompany(company.get());
                        } else view.showErrorMessage("존재하지 않는 정보입니다.");
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.showErrorMessage("아이템을 불러올 수 없습니다.");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
