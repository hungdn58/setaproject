package com.example.hoang.newsproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by hoang on 3/10/2016.
 */
public class RSSItem implements Serializable {

    private static final long serialVersionUID = 1L;
    private String _title = null;
    private String _description = null;
    private String _date = null;
    private String _image = null;
    private String _link = null;
    private String _source = null;
    private int count = 0;
    private int background = 0;

    void setTitle(String title) {
        _title = title;
    }

    void setDescription(String description) {
        _description = description;
    }

    void setDate(String pubdate) {
        _date = pubdate;
    }

    void setImage(String image) {
        _image = image;
    }

    public String getTitle() {
        return _title;
    }

    public String getDescription() {
        return _description;
    }

    public String getDate() {
        return _date;
    }

    public String getImage() {
        return _image;
    }

    public void set_link(String _link) {
        this._link = _link;
    }

    public String get_link() {
        return _link;
    }

    public String get_source() {
        return _source;
    }

    public void set_source(String _source) {
        this._source = _source;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
