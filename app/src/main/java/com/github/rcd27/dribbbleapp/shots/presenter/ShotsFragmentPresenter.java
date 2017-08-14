package com.github.rcd27.dribbbleapp.shots.presenter;


import com.github.rcd27.dribbbleapp.DribbbleApplication;
import com.github.rcd27.dribbbleapp.shots.ShotsContract;
import com.github.rcd27.dribbbleapp.shots.data.ShotsModel;
import com.github.rcd27.dribbbleapp.shots.data.mappers.RequiredShotsMapper;
import com.github.rcd27.dribbbleapp.utils.ConnectivityUtils;

import javax.inject.Inject;

public class ShotsFragmentPresenter implements ShotsContract.Presenter {

    @Inject
    public ShotsModel shotsModel;

    @Inject
    public RequiredShotsMapper requiredShotsMapper;

    @Inject
    public ConnectivityUtils connectivityUtils;

    private ShotsContract.View view;

    // Для переключения страниц. Можно использовать Link Header.
    // см.: http://developer.dribbble.com/v1/#pagination
    private int pageNumber = 1;

    @Inject
    public ShotsFragmentPresenter() {
    }

    public ShotsFragmentPresenter(ShotsContract.View view) {
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
