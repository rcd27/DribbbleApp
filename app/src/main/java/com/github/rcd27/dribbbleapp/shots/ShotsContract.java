package com.github.rcd27.dribbbleapp.shots;


import android.support.annotation.NonNull;

import com.github.rcd27.dribbbleapp.shots.data.ShotVisualObject;
import com.github.rcd27.dribbbleapp.shots.view.ShotsRecyclerViewAdapter;

import java.util.List;

import io.reactivex.Single;

public interface ShotsContract {

    interface Model {

        Single<List<ShotVisualObject>> getShots(int forPage, int shotsAmount);

        ShotVisualObject getShotById(int id);

    }

    interface View {

        void showError(@NonNull String errorMessage);

        void update(@NonNull List<ShotVisualObject> shots);

        void scrollToBottom();

        void showShotDetail(ShotsRecyclerViewAdapter.ShotViewHolder holder,
                            @NonNull ShotVisualObject shotVisualObject);

    }

    interface Presenter {

        void updateActual();

        void checkIfOnlineAndUpdateActual();

        void onCardClicked(ShotsRecyclerViewAdapter.ShotViewHolder holder, int shotId);
    }

    interface Interactor {

        Single<List<ShotVisualObject>> getFithtyShotsForOnePage();

        boolean isOnline();

        ShotVisualObject getShotFromRepository(int id);

    }
}
