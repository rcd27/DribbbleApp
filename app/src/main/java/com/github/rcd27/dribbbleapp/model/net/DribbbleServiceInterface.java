package com.github.rcd27.dribbbleapp.model.net;


import com.github.rcd27.dribbbleapp.model.objects.ShotVisualObject;

import java.util.List;

import io.reactivex.Single;

//TODO FIXME: пока что используется Model

public interface DribbbleServiceInterface {

    Single<List<ShotVisualObject>> getShots(int forPage, int shotsPerPage);

}
