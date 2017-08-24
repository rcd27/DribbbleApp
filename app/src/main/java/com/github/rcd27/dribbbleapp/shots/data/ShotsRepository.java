package com.github.rcd27.dribbbleapp.shots.data;


import android.util.SparseArray;

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
    private final SparseArray<ShotVisualObject> currentShotsForPage;

    public ShotsRepository(DribbbleShotsApi dribbbleShotsApi, RequiredShotsMapper requiredMapper) {
        this.dribbbleShotsApi = dribbbleShotsApi;
        this.uiThread = AndroidSchedulers.mainThread();
        this.ioThread = Schedulers.io();
        this.requiredShotsMapper = requiredMapper;
        this.currentShotsForPage = new SparseArray<>();

        schedulersTransformer = observable -> observable
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread);

    }

    @SuppressWarnings("unchecked")
    @Override
    public Single<List<ShotVisualObject>> getShots(int forPage, int shotsAmount) {
        return dribbbleShotsApi.getShots(forPage, shotsAmount)
                .map(requiredShotsMapper)
                .map(shotVisualObjects -> {
                    currentShotsForPage.clear();
                    for (ShotVisualObject shot : shotVisualObjects) {
                        currentShotsForPage.put(shot.id, shot);
                    }
                    return shotVisualObjects;
                })
                .compose(schedulersTransformer);
    }

    @Override
    public ShotVisualObject getShotById(int id) {
        return currentShotsForPage.get(id);
    }
}
