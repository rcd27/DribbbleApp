package rcd27.github.com.dribbbleapp.model;

/*
 * Visual object для предоставления адаптеру и отображения shot'ов.
 */

import android.support.annotation.NonNull;

public class Shot {

    public final String imageUrl;
    public final String title;
    public final String description;

    public Shot(@NonNull String imageUrl,
                @NonNull String title,
                @NonNull String description) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
    }
}
