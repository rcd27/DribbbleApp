package rcd27.github.com.dribbbleapp.model;

/*
 * Мок visual object для адаптера ListView.
 */

import android.graphics.drawable.Drawable;

public class Shot {

    public final Drawable shotImage;
    public final String title;
    public final String description;

    public Shot(Drawable shotImage, String title, String description) {
        this.shotImage = shotImage;
        this.title = title;
        this.description = description;
    }
}
