package com.example.hoang.newsproject;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

/**
 * Created by hoang on 3/12/2016.
 */

public class RSSFeed implements Serializable {

    private static final long serialVersionUID = 1L;
    private int _itemcount = 0;
    private String title;
    private List<RSSItem> _itemlist;

    RSSFeed() {
        _itemlist = new Vector<RSSItem>(0);
    }

    void addItem(RSSItem item) {
        _itemlist.add(item);
        _itemcount++;
    }

    public RSSItem getItem(int location) {
        return _itemlist.get(location);
    }

    public int getItemCount() {
        return _itemcount;
    }

    public List<RSSItem> get_itemlist() {
        return _itemlist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
