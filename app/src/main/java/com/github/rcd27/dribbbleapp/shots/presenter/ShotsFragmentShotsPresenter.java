package com.github.rcd27.dribbbleapp.shots.presenter;


import javax.inject.Inject;

import com.github.rcd27.dribbbleapp.DribbbleApplication;
import com.github.rcd27.dribbbleapp.shots.model.ShotsModel;
import com.github.rcd27.dribbbleapp.shots.model.mappers.RequiredShotsMapper;
import com.github.rcd27.dribbbleapp.shots.view.fragments.ShotsFragmentShotsView;
import com.github.rcd27.dribbbleapp.utils.ConnectivityUtils;

public class ShotsFragmentShotsPresenter implements ShotsPresenter {

    @Inject
    public ShotsModel shotsModel;

    @Inject
    public RequiredShotsMapper requiredShotsMapper;

    @Inject
    public ConnectivityUtils connectivityUtils;

    private ShotsFragmentShotsView view;

    // Для переключения страниц. Можно использовать Link Header.
    // см.: http://developer.dribbble.com/v1/#pagination
    private int pageNumber = 1;

    @Inject
    public ShotsFragmentShotsPresenter() {
    }

    public ShotsFragmentShotsPresenter(ShotsFragmentShotsView view) {
        this.view = view;
        DribbbleApplication.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void updateActual() {
        shotsModel.getShots(pageNumber, 50)
                .map(requiredShotsMapper)
                .doOnSuccess(view::update)
                .subscribe();
        pageNumber++;
    }

    @Override
    public void checkIfOnlineAndUpdateActual() {
        if (connectivityUtils.isOnline()) {
            updateActual();
        } else {
            view.showError("No Internet Connection");
        }
    }
}
