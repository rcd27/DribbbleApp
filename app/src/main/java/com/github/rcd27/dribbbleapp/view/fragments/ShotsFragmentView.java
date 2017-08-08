package com.github.rcd27.dribbbleapp.view.fragments;

import android.support.annotation.NonNull;

import com.github.rcd27.dribbbleapp.model.objects.ShotVisualObject;
import com.github.rcd27.dribbbleapp.view.View;

import java.util.List;


public interface ShotsFragmentView extends View {

    void update(@NonNull List<ShotVisualObject> shots);

}
