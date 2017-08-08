package com.github.rcd27.dribbbleapp.model;


import com.github.rcd27.dribbbleapp.DribbbleApplication;
import com.github.rcd27.dribbbleapp.model.net.DribbbleApi;
import com.github.rcd27.dribbbleapp.model.objects.ShotDataTransferObject;
import com.github.rcd27.dribbbleapp.other.Const;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;

public class ModelImpl implements Model {

    private final SingleTransformer schedulersTransformer;

    @Inject
    DribbbleApi dribbbleApi;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        DribbbleApplication.getInstance().getAppComponent().inject(this);
        schedulersTransformer = observable -> observable
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread);
    }

    @Override
    public Single<List<ShotDataTransferObject>> getShots(int forPage, int shotsAmount) {
        return dribbbleApi.getShots(forPage, shotsAmount)
                .compose(schedulersTransformer);
    }
}
