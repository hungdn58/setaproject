package com.example.hoang.datingproject.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by hoang on 4/4/2016.
 */
public class FeedModel implements Serializable{
    private int feedIcon;
    private String feedTitle;
    private String feedDescription;
    private String feedView;
    private String feedPubDate;
    private Bitmap image;

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

    public FeedModel(int feedIcon, String feedTitle, String feedDescription) {
        this.feedIcon = feedIcon;
        this.feedTitle = feedTitle;
        this.feedDescription = feedDescription;
    }

    public FeedModel(int feedIcon, String feedTitle, String feedDescription, Bitmap mimage) {
        this.feedIcon = feedIcon;
        this.feedTitle = feedTitle;
        this.feedDescription = feedDescription;
        this.image = mimage;
    }

    public FeedModel(int feedIcon, String feedTitle, Bitmap mimage) {
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

    public int getFeedIcon() {
        return feedIcon;
    }

    public void setFeedIcon(int feedIcon) {
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
