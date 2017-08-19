package com.github.rcd27.dribbbleapp.shots.presenter;


import android.util.Log;
import android.view.View;

import com.github.rcd27.dribbbleapp.shots.ShotsContract;

public class ShotsPresenter implements ShotsContract.Presenter {

    public static final String NO_INTERNET_CONNECTION = "No Internet Connection";

    private ShotsContract.Interactor shotsInteractor;
    private ShotsContract.View view;

    public ShotsPresenter(ShotsContract.Interactor interactor, ShotsContract.View view) {
        this.shotsInteractor = interactor;
        this.view = view;
    }

    @Override
    public void updateActual() {
        shotsInteractor
                .getFithtyShotsForOnePage()
                .doOnSuccess(view::update)
                .doOnError(Throwable::printStackTrace)
                .subscribe();
        view.scrollToBottom();
    }

    @Override
    public void checkIfOnlineAndUpdateActual() {
        if (shotsInteractor.isOnline()) {
            updateActual();
        } else {
            view.showError(NO_INTERNET_CONNECTION);
        }
    }

    @Override
    public void onCardClicked(View card) {
        Log.d("eventLog", "onCardClicked: presenter handles click!");
    }
}
