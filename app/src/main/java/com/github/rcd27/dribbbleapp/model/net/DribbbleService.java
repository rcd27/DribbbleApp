package com.github.rcd27.dribbbleapp.model.net;

import com.github.rcd27.dribbbleapp.model.mappers.RequiredShotsMapper;
import com.github.rcd27.dribbbleapp.model.objects.ShotVisualObject;

import java.util.List;

import io.reactivex.Single;

/**
 * Реализация интерфейса сервиса Dribbble
 */

public class DribbbleService implements DribbbleServiceInterface{

    private final DribbbleApiInterface apiInterface;
    private final RequiredShotsMapper requiredShotsMapper;

    public DribbbleService(DribbbleApiInterface apiInterface, RequiredShotsMapper requiredShotsMapper) {
        this.apiInterface = apiInterface;
        this.requiredShotsMapper = requiredShotsMapper;
    }

    @Override
    public Single<List<ShotVisualObject>> getShots(int forPage, int shotsPerPage) {
        return apiInterface
                .getShots(forPage, shotsPerPage)
                .map(requiredShotsMapper);
    }
}
