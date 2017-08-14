package com.github.rcd27.dribbbleapp.shots.data;


import com.github.rcd27.dribbbleapp.shots.ShotsContract;
import com.github.rcd27.dribbbleapp.utils.ConnectivityUtils;

import java.util.List;

import io.reactivex.Single;

public class ShotsInteractor implements ShotsContract.Interactor {

    private final ShotsContract.Model model;
    private final ConnectivityUtils connectivityUtils;
    private final RequiredShotsMapper requiredShotsMapper;

    private int pageNumber = 0;

    public ShotsInteractor(ShotsContract.Model model,
                           ConnectivityUtils connectivityUtils,
                           RequiredShotsMapper requiredShotsMapper) {
        this.model = model;
        this.connectivityUtils = connectivityUtils;
        this.requiredShotsMapper = requiredShotsMapper;
    }

    @Override
    public Single<List<ShotVisualObject>> getFithtyShotsForOnePage() {
        pageNumber++;
        return model.getShots(pageNumber, 50)
                .map(requiredShotsMapper);
    }

    @Override
    public boolean isOnline() {
        return connectivityUtils.isOnline();
    }
}
