package com.github.rcd27.dribbbleapp.shots.data;


import com.github.rcd27.dribbbleapp.shots.ShotsContract;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShotsRepository implements ShotsContract.Model {

    private final SingleTransformer schedulersTransformer;
    private final DribbbleShotsApi dribbbleShotsApi;
    private final Scheduler uiThread;
    private final Scheduler ioThread;
    private final RequiredShotsMapper requiredShotsMapper;

    public ShotsRepository(DribbbleShotsApi dribbbleShotsApi, RequiredShotsMapper requiredMapper) {
        this.dribbbleShotsApi = dribbbleShotsApi;
        this.uiThread = AndroidSchedulers.mainThread();
        this.ioThread = Schedulers.io();
        this.requiredShotsMapper = requiredMapper;

        schedulersTransformer = observable -> observable
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread);
    }

    @Override
    public Single<List<ShotVisualObject>> getShots(int forPage, int shotsAmount) {
        return dribbbleShotsApi.getShots(forPage, shotsAmount)
                .compose(schedulersTransformer)
                .map(requiredShotsMapper);
    }
}
