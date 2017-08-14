package com.github.rcd27.dribbbleapp.shots.presenter;


import com.github.rcd27.dribbbleapp.shots.ShotsContract;

public class ShotsPresenter implements ShotsContract.Presenter {

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
                .subscribe();
    }

    @Override
    public void checkIfOnlineAndUpdateActual() {
        if (shotsInteractor.isOnline()) {
            updateActual();
        } else {
            view.showError("No Internet Connection");
        }
    }
}
