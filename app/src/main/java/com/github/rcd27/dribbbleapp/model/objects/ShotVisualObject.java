package com.github.rcd27.dribbbleapp.model.objects;

/*
 * Visual object для предоставления адаптеру и отображения shot'ов.
 */

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ShotVisualObject {

    public final String imageUrl;
    public final String title;
    @NonNull
    public final String description;

    public ShotVisualObject(@NonNull String imageUrl,
                            @NonNull String title,
                            @Nullable String description) {
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
