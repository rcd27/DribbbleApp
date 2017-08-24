package com.github.rcd27.dribbbleapp.shots.data;

/*
 * Visual object для предоставления адаптеру и отображения shot'ов.
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ShotVisualObject {

    public final int id;
    public final String imageUrl;
    public final String title;
    @NonNull
    public final String description;

    public ShotVisualObject(int id,
                            @NonNull String imageUrl,
                            @NonNull String title,
                            @Nullable String description) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;

        // Были проблемы с десереализацией GSON и тем, чтобы задачть дефолтное значение.
        // Пришлось лепить такой вот костыль.
        if (description != null) {
            this.description = description;
        } else {
            this.description = "***no description***";
        }
    }
}
