package com.github.rcd27.dribbbleapp.shots.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.rcd27.dribbbleapp.R;
import com.github.rcd27.dribbbleapp.shots.data.ShotVisualObject;

public class ShotDetailFragment extends Fragment {

    private final ShotVisualObject currentShot;

    public ShotDetailFragment(@NonNull ShotVisualObject shotVisualObject) {
        this.currentShot = shotVisualObject;
    }

    public static ShotDetailFragment newInstance(@NonNull ShotVisualObject shotVisualObject) {
        return new ShotDetailFragment(shotVisualObject);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shot_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = (ImageView) view.findViewById(R.id.image);

    }
}
