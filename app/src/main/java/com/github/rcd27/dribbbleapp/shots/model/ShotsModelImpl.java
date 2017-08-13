package com.github.rcd27.dribbbleapp.shots.model;


import com.github.rcd27.dribbbleapp.DribbbleApplication;
import com.github.rcd27.dribbbleapp.shots.model.net.DribbbleApiInterface;
import com.github.rcd27.dribbbleapp.shots.model.objects.ShotDataTransferObject;
import com.github.rcd27.dribbbleapp.other.Const;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleTransformer;

public class ShotsModelImpl implements ShotsModel {

    private final SingleTransformer schedulersTransformer;

    @Inject
    DribbbleApiInterface dribbbleApiInterface;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public ShotsModelImpl() {
        DribbbleApplication.getInstance().getAppComponent().inject(this);
        schedulersTransformer = observable -> observable
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread);
    }

    @Override
    public Single<List<ShotDataTransferObject>> getShots(int forPage, int shotsAmount) {
        return dribbbleApiInterface.getShots(forPage, shotsAmount)
                .compose(schedulersTransformer);
    }
}
