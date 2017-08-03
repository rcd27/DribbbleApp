package rcd27.github.com.dribbbleapp.model;

/*
 * Visual object для предоставления адаптеру и отображения shot'ов.
 */

import android.support.annotation.NonNull;

public class ShotVisualObject {

    public final String imageUrl;
    public final String title;
    public final String description;

    public ShotVisualObject(@NonNull String imageUrl,
                            @NonNull String title,
                            @NonNull String description) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
    }
}
