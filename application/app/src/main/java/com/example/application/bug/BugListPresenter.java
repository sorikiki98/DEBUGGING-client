package com.example.application.bug;

import android.util.Log;

import com.example.application.data.Bug;
import com.example.application.data.source.repository.BugRepository;

import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;

public class BugListPresenter implements BugListContract.Presenter {
    private final BugRepository bugsRepository;

    private final BugListContract.View view;

    private final Scheduler mainScheduler;

    private Subscription subscription;

    @Inject
    BugListPresenter(BugRepository bugsRepository, BugListContract.View view, Scheduler scheduler) {
        this.bugsRepository = bugsRepository;
        this.view = view;
        this.mainScheduler = scheduler;
    }

    @Override
    public void subscribe() {
        loadBugs();
    }

    @Override
    public void unsubscribe() {
        subscription.cancel();
    }

    @Override
    public void loadBugs() {
        bugsRepository.getBugs()
                .observeOn(mainScheduler)
                .subscribe(new FlowableSubscriber<List<Bug>>() {
                    @Override
                    public void onSubscribe(@NonNull Subscription s) {
                        s.request(Long.MAX_VALUE);
                        subscription = s;
                    }

                    @Override
                    public void onNext(List<Bug> bugs) {
                        Log.d("BugsListPresenter", bugs.toString());
                        view.showBugs(bugs);
                    }

                    @Override
                    public void onError(Throwable t) {
                        view.showErrorMessage("목록을 불러올 수 없습니다.");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("BugsListPresenter", "complete");
                    }
                });
    }

    @Override
    public void loadBugWithId() {

    }

    @Override
    public void survey() {

    }
}
