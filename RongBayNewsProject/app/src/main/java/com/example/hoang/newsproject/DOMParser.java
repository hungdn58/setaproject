package com.example.hoang.newsproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.WireFeed;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.module.Module;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndContent;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEnclosure;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndEntry;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndFeed;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.synd.SyndImage;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.FeedException;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.SyndFeedInput;
import com.google.code.rome.android.repackaged.com.sun.syndication.io.XmlReader;

import org.jdom.Element;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.DTDHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by hoang on 3/12/2016.
 */
public class DOMParser {

    private String title;
    public SyndFeed mfeed;

    public DOMParser(String title){
        this.title = title;
    }

    private RSSFeed _feed = new RSSFeed();

    private String getMonth(String month) {
        String m = "";
        switch (month){
            case "Jan": m = "01";
                break;
            case "Feb": m = "02";
                break;
            case "Mar": m = "03";
                break;
            case "Apr": m = "04";
                break;
            case "May": m = "05";
                break;
            case "Jun": m = "06";
                break;
            case "Jul": m = "07";
                break;
            case "Aug": m = "08";
                break;
            case "Sep": m = "09";
                break;
            case "Oct": m = "10";
                break;
            case "Nov": m = "11";
                break;
            case "Dec": m = "12";
                break;
        }
        return m;
    }

    public RSSFeed getInfor(String rss) throws IOException, FeedException {

//        URL url  = new URL("http://dantri.com.vn/giao-duc-khuyen-hoc.rss");
//        XmlReader reader = null;
//        reader = new XmlReader(url);
//        SyndFeed mfeed = new SyndFeedInput().build(reader);

        Thread.currentThread().setContextClassLoader(getClass().getClassLoader());

        RssAtomFeedRetriever rssAtomFeedRetriever = new RssAtomFeedRetriever();
        mfeed = rssAtomFeedRetriever.getMostRecentNews(rss);

        ArrayList<SyndEntry> list = new ArrayList<SyndEntry>();
        list = (ArrayList<SyndEntry>) mfeed.getEntries();

        for (int i = 0; i < list.size(); i++){
            SyndEntry syndEntry = (SyndEntry) list.get(i);
            RSSItem item = new RSSItem();
            item.set_source("rongbay.com");

            StringHelper stringHelper = new StringHelper();
            byte[] b = syndEntry.getTitle().getBytes("UTF-8");
            String normal = new String(b);

            item.setTitle(normal);
            Log.d("NEWSEXAMPLEDISCRIP", stringHelper.convertToUTF8(syndEntry.getTitle()));
            item.setDescription(syndEntry.getDescription().getValue());
            Log.d("NEWSEXAMPLEDISCRIP", item.getDescription());
//            List<Element> foreignMarkups = (List<Element>) syndEntry.getForeignMarkup();
//            for (Element foreignMarkup : foreignMarkups) {
//                String imgURL = foreignMarkup.getAttribute("url").getValue();
//                Log.d("IMAGEEXAMPLE", imgURL);
//                //read width and height
//            }
//            List<SyndEnclosure> encls = syndEntry.getEnclosures();
//            if(!encls.isEmpty()){
//                for(SyndEnclosure e : encls){
//                    String imgURL = e.getUrl().toString();
//                    Log.d("IMAGEEXAMPLE1", imgURL);
//                }
//            }
//            String html = item.getDescription();
//            int start = html.indexOf("src") + 5;
//            int end = html.indexOf('"', start);
//            if (start < end){
//                String src = html.substring(start, end);
//                item.setImage(src);
//
//            }
            item.set_link(syndEntry.getLink());
//            item.setDate(syndEntry.getPublishedDate().getYear() + "/" + syndEntry.getPublishedDate().getMonth() + "/" +syndEntry.getPublishedDate().getDay());
//            Log.d("NEWSEXAMPLEDISCRIP", syndEntry.getPublishedDate().toString());
            String []date = syndEntry.getPublishedDate().toString().split(" ");
////                            Log.d("PUBDATE", date[3] + "年" + date[2] + "月" + date[1] + "日");
            if (date.length < 3){
                item.setDate(date[0]);
            }else {
                String pubDate = date[5] + "/" + getMonth(date[1]) + "/" + date[2];
                item.setDate(pubDate);

            }
//            Log.d("NEWSEXAMPLEDISCRIP", item.getDate());
            _feed.addItem(item);
        }
        return _feed;
    }
}
