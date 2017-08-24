package com.github.rcd27.dribbbleapp.shots.data;


import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ShotDataTransferObject {
    @SerializedName("id")
    public final int id;

    @SerializedName("title")
    public final String title;

    @SerializedName("description")
    public final String description;

    @SerializedName("width")
    public final int width;

    @SerializedName("height")
    public final int height;

    @SerializedName("images")
    public final Map<String, String> images;

    @SerializedName("isAnimated")
    public final boolean isAnimated;

    public ShotDataTransferObject(int id,
                                  String title,
                                  @Nullable String description,
                                  int width,
                                  int height,
                                  Map<String, String> images,
                                  boolean isAnimated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.width = width;
        this.height = height;
        this.images = images;
        this.isAnimated = isAnimated;
    }

    public String toString() {
        return "--------------------------------" +
                "\nID: " + id +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nWidth: " + width +
                "\nHeight: " + height +
                "\nImages: " + images.toString() +
                "\nAnimated: " + isAnimated;
    }
}
