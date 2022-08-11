package com.example.application.mypage;

import com.example.application.data.MySurvey;
import com.example.application.data.source.repository.UserRepository;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class MyPageSurveyDetailListPresenter implements MyPageSurveyDetailListContract.Presenter {
    private final UserRepository userRepository;
    private final MyPageSurveyDetailListContract.View view;
    private final Scheduler mainScheduler;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MyPageSurveyDetailListPresenter(
            UserRepository userRepository,
            MyPageSurveyDetailListContract.View view,
            Scheduler scheduler
    ) {
        this.userRepository = userRepository;
        this.view = view;
        this.mainScheduler = scheduler;
    }

    @Override
    public void subscribe() {
        loadMyPageSurveyList();
        loadUserName();
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void loadMyPageSurveyList() {
        userRepository.getMySurveyList()
                .observeOn(mainScheduler)
                .subscribe(new MaybeObserver<List<MySurvey>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull List<MySurvey> mySurveys) {
                        view.showMyPageSurveyList(mySurveys);
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
