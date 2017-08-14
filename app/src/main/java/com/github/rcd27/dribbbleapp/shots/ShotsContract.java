package com.github.rcd27.dribbbleapp.shots;


import android.support.annotation.NonNull;

import com.github.rcd27.dribbbleapp.shots.data.objects.ShotVisualObject;

import java.util.List;

public interface ShotsContract {

    interface View {

        void showError(@NonNull String errorMessage);

        void update(@NonNull List<ShotVisualObject> shots);

    }

    interface Presenter {

        void updateActual();

        void checkIfOnlineAndUpdateActual();

    }
}
