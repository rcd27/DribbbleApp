package com.github.rcd27.dribbbleapp.shots.view;


import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

public class ShotDetailsTransition extends TransitionSet {

    public ShotDetailsTransition() {
        setOrdering(ORDERING_TOGETHER);
        addTransition(new ChangeBounds())
                .addTransition(new ChangeTransform())
                .addTransition(new ChangeImageTransform());
    }
}
