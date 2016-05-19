package com.example.hoang.datingproject.Model;

/**
 * Created by hoang on 4/5/2016.
 */
public class MessageModel {
    private int message_icon;
    private String message_title;
    private String message_content;
    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public MessageModel(int message_icon, String message_title, String message_content) {
        this.message_icon = message_icon;
        this.message_title = message_title;
        this.message_content = message_content;
    }

    public int getMessage_icon() {
        return message_icon;
    }

    public void setMessage_icon(int message_icon) {
        this.message_icon = message_icon;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }
}
