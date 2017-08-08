package com.github.rcd27.dribbbleapp.model;


import java.util.List;

import io.reactivex.Single;
import com.github.rcd27.dribbbleapp.model.objects.ShotDataTransferObject;

public interface Model {

    Single<List<ShotDataTransferObject>> getShots(int forPage, int shotsAmount);

}
