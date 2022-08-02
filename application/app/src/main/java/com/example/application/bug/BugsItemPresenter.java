package com.example.application.bug;

import com.example.application.data.Bug;
import com.example.application.data.source.repository.BugsRepository;

import org.reactivestreams.Subscription;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;

public class BugsItemPresenter implements BugsItemContract.Presenter {
    private final BugsRepository bugsRepository;

    private final BugsItemContract.View view;

    private final Scheduler mainScheduler;

    private int bugId;

    private Subscription subscription;

    @Inject
    BugsItemPresenter(BugsRepository bugsRepository, BugsItemContract.View view, Scheduler scheduler) {
        this.bugsRepository = bugsRepository;
        this.view = view;
        this.mainScheduler = scheduler;
    }

    @Override
    public void subscribe() {
        loadBugWithId(bugId);
    }

    @Override
    public void unsubscribe() {
        subscription.cancel();
    }

    @Override
    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    @Override
    public void loadBugWithId(int bugId) {
        bugsRepository.getBug(bugId)
                .observeOn(mainScheduler)
                .subscribe(new FlowableSubscriber<Optional<Bug>>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                        s.request(Long.MAX_VALUE);
                        subscription = s;
                    }

                    @Override
                    public void onNext(Optional<Bug> bug) {
                        if (bug.isPresent()) {
                            view.showBug(bug.get());
                        }
                        else view.showErrorMessage("존재하지 않는 정보입니다.");
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
