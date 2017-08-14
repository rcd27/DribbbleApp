package com.github.rcd27.dribbbleapp.shots;


import android.support.annotation.NonNull;

import com.github.rcd27.dribbbleapp.shots.data.ShotVisualObject;

import java.util.List;

import io.reactivex.Single;

public interface ShotsContract {

    interface Model {

        Single<List<ShotVisualObject>> getShots(int forPage, int shotsAmount);

    }

    interface View {

        void showError(@NonNull String errorMessage);

        void update(@NonNull List<ShotVisualObject> shots);

    }

    interface Presenter {

        void updateActual();

        void checkIfOnlineAndUpdateActual();

    }

    interface Interactor {

        Single<List<ShotVisualObject>> getFithtyShotsForOnePage();

        boolean isOnline();

    }
}
