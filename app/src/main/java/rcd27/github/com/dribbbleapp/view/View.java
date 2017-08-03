package rcd27.github.com.dribbbleapp.view;


import android.support.annotation.NonNull;

import java.util.List;

import rcd27.github.com.dribbbleapp.model.ShotVisualObject;

public interface View {

    void update(@NonNull List<ShotVisualObject> shots);

}
