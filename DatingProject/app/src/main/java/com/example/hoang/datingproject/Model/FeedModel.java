package com.example.hoang.datingproject.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by hoang on 4/4/2016.
 */
public class FeedModel implements Serializable{
    private String feedIcon;
    private String feedTitle;
    private String feedDescription;
    private String feedView;
    private String feedPubDate;
    private Bitmap image;
    private String id;

    public String getFeedView() {
        return feedView;
    }

    public void setFeedView(String feedView) {
        this.feedView = feedView;
    }

    public String getFeedPubDate() {
        return feedPubDate;
    }

    public void setFeedPubDate(String feedPubDate) {
        this.feedPubDate = feedPubDate;
    }

    public FeedModel() {}

    public FeedModel(String feedIcon, String feedTitle, String feedDescription, String id) {
        this.feedIcon = feedIcon;
        this.feedTitle = feedTitle;
        this.feedDescription = feedDescription;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FeedModel(String feedIcon, String feedTitle, String feedDescription, Bitmap mimage, String id) {
        this.feedIcon = feedIcon;
        this.feedTitle = feedTitle;
        this.feedDescription = feedDescription;
        this.image = mimage;
        this.id = id;

    }

    public FeedModel(String feedIcon, String feedTitle, Bitmap mimage) {
        this.feedIcon = feedIcon;
        this.feedTitle = feedTitle;
        this.image = mimage;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getFeedIcon() {
        return feedIcon;
    }

    public void setFeedIcon(String feedIcon) {
        this.feedIcon = feedIcon;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }

    public String getFeedDescription() {
        return feedDescription;
    }

    public void setFeedDescription(String feedDescription) {
        this.feedDescription = feedDescription;
    }
}
