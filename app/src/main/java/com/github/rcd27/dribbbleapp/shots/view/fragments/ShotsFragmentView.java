package com.github.rcd27.dribbbleapp.shots.view.fragments;

import android.support.annotation.NonNull;

import com.github.rcd27.dribbbleapp.shots.data.objects.ShotVisualObject;
import com.github.rcd27.dribbbleapp.shots.view.ShotsView;

import java.util.List;


public interface ShotsFragmentView extends ShotsView {

    void update(@NonNull List<ShotVisualObject> shots);

}
