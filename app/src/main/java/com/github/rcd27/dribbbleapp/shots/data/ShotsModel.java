package com.github.rcd27.dribbbleapp.shots.data;


import com.github.rcd27.dribbbleapp.shots.data.objects.ShotDataTransferObject;

import java.util.List;

import io.reactivex.Single;

public interface ShotsModel {

    Single<List<ShotDataTransferObject>> getShots(int forPage, int shotsAmount);

}
