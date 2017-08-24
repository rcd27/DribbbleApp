package com.github.rcd27.dribbbleapp.shots.data;


import com.github.rcd27.dribbbleapp.shots.ShotsContract;
import com.github.rcd27.dribbbleapp.utils.ConnectivityUtils;

import java.util.List;

import io.reactivex.Single;

public class ShotsInteractor implements ShotsContract.Interactor {

    private final ShotsContract.Model model;
    private final ConnectivityUtils connectivityUtils;

    private int pageNumber = 0;

    public ShotsInteractor(ShotsContract.Model model,
                           ConnectivityUtils connectivityUtils) {
        this.model = model;
        this.connectivityUtils = connectivityUtils;
    }

    @Override
    public Single<List<ShotVisualObject>> getFithtyShotsForOnePage() {
        pageNumber++;
        return model.getShots(pageNumber, 50);
    }

    @Override
    public boolean isOnline() {
        return connectivityUtils.isOnline();
    }

    @Override
    public ShotVisualObject getShotFromRepository(int id) {
        return model.getShotById(id);
    }
}
