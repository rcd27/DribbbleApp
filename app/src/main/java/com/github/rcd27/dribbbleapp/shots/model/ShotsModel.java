package com.github.rcd27.dribbbleapp.shots.model;


import com.github.rcd27.dribbbleapp.shots.model.objects.ShotDataTransferObject;

import java.util.List;

import io.reactivex.Single;

public interface ShotsModel {

    Single<List<ShotDataTransferObject>> getShots(int forPage, int shotsAmount);

}
