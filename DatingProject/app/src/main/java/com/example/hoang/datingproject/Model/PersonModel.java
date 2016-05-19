package com.example.hoang.datingproject.Model;

import android.graphics.Bitmap;

/**
 * Created by hoang on 4/4/2016.
 */
public class PersonModel {
    private Bitmap image;
    private int id;
    private String nickname;
    private String uid;

    public PersonModel(Bitmap image, String nickname, String uid) {
        this.image = image;
        this.nickname = nickname;
        this.uid = uid;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
