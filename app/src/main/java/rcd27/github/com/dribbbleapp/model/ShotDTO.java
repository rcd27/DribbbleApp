package rcd27.github.com.dribbbleapp.model;


import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class ShotDTO {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("images")
    private Map<String, String> images;

    @SerializedName("animated")
    private boolean animated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public void setImages(Map<String, String> images) {
        this.images = images;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
    }

    @Override
    public String toString() {
        return  "--------------------------------" +
                "\nID: " + id +
                "\nTitle: " + title +
                "\nDescription: " + description +
                "\nWidth: " + width +
                "\nHeight: " + height +
                "\nImages: " + images.toString() +
                "\nAnimated: " + animated;
    }
}
