package com.example.hoang.newsproject;

import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by hoang on 3/12/2016.
 */
public class DOMParser {

    private String title;

    public DOMParser(String title){
        this.title = title;
    }

    private RSSFeed _feed = new RSSFeed();

    public RSSFeed parseXml(String rss_url) {

        URL url = null;
        try {
            url = new URL(rss_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document document = db.parse(new InputSource(url.openStream()));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("item");
            int length = nodeList.getLength();

            for (int i = 0; i < length; i++) {
                Node currentNode = nodeList.item(i);
                RSSItem item = new RSSItem();
                item.set_source(title);

                NodeList nodeChild = currentNode.getChildNodes();
                int childLength = nodeChild.getLength();
//                Log.d("NEWSEXAMPLE", childLength + "size");

                for (int j = 0; j < childLength; j++) {
                    Node thisNode = nodeChild.item(j);
                    String theString = null;
                    String nodeName = thisNode.getNodeName();

//                    if (nodeChild.item(j) != null && nodeChild.item(j).getFirstChild() != null) {
//
//                    }
                    theString = nodeChild.item(j).getFirstChild().getNodeValue();

                    if (theString != null) {
                        if ("title".equals(nodeName)) {
                            // Node name is equals to 'title' so set the Node
                            // value to the Title in the RSSItem.
                            item.setTitle(theString);
                        } else if ("description".equals(nodeName)) {
                            item.setDescription(theString);

                            // Parse the html description to get the image url
                            String html = theString;
                            org.jsoup.nodes.Document docHtml = Jsoup
                                    .parse(html);
                            Elements imgEle = docHtml.select("img");
                            item.setImage("http:");
                            item.setImage(item.getImage() + imgEle.attr("src"));
                            Log.d("NEWSEXAMPLE", theString);

                        } else if ("link".equals(nodeName)) {
                            item.set_link(theString);
                        } else if ("pubDate".equals(nodeName)) {

                            // We replace the plus and zero's in the date with
                            // empty string
//                            String formatedDate = theString.replace(" +0000",
//                                    "");
                            String []date = theString.split(" ");
                            Log.d("PUBDATE", date[3] + "年" + date[2] + "月" + date[1] + "日");
                            String pubDate = date[3] + "年" + getMonth(date[2]) + "月" + date[1] + "日";
                            item.setDate(pubDate);
                        }
                    }
                }
                _feed.addItem(item);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return _feed;
    }

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

}
