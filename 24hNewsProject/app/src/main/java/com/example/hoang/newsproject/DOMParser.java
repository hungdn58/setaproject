package com.example.hoang.newsproject;

import android.util.Log;
import android.widget.Toast;

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

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
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
                item.set_source("24h.com.vn");

                NodeList nodeChild = currentNode.getChildNodes();
                int childLength = nodeChild.getLength();
//                Log.d("NEWSEXAMPLE", childLength + "size");

                for (int j = 0; j < childLength; j++) {
                    Node thisNode = nodeChild.item(j);
                    String theString = null;
                    String nodeName = thisNode.getNodeName();

                    if (nodeChild.item(j) != null && nodeChild.item(j).getFirstChild() != null) {
                        theString = nodeChild.item(j).getFirstChild().getNodeValue();
                    }

                    if (theString != null) {
                        if ("title".equals(nodeName)) {
                            // Node name is equals to 'title' so set the Node
                            // value to the Title in the RSSItem.
                            item.setTitle(theString);

                        } else if ("description".equals(nodeName)) {
                            String theString1 = nodeChild.item(j).getFirstChild().getNextSibling().getNodeValue();
                            item.setDescription(theString1);
                            Log.d("NEWSEXAMPLEDES", theString1.length() + "length");
                            // Parse the html description to get the image url
//                            String html = theString;
//                            org.jsoup.nodes.Document docHtml = Jsoup
//                                    .parse(html);
//                            Elements imgEle = docHtml.select("img");
//                            item.setImage("ht patp:");
//                            item.setImage(item.getImage() + imgEle.attr("src"));
                            String html = theString1;
                            int start = html.indexOf("src") + 5;
                            int end = html.indexOf('\'', start);

                            if (start < end){
                                String src = html.substring(start, end);
                                item.setImage(src);
                                Log.d("NEWSEXAMPLEIMAGE", src);
                            }
//                                Log.d("NEWSEXAMPLEDISCRIP", src);

                        } else if ("link".equals(nodeName)) {
                            item.set_link(theString);
                        } else if ("pubDate".equals(nodeName)) {

                            // We replace the plus and zero's in the date with
                            // empty string
//                            String formatedDate = theString.replace(" +0000",
//                                    "");
                            String []date = theString.split(" ");
                            if (date.length < 3){
                                String []date1 = date[0].split("-");
                                String pubDate = date1[2] + "/" + date1[1] + "/" + date1[0];
                                item.setDate(pubDate);;
                            }else {
                                String pubDate = date[3] + "/" + getMonth(date[2]) + "/" + date[1];
                                item.setDate(pubDate);
                            }
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

    public Document getDomElement(String xml) {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setCoalescing(true);
        dbf.setNamespaceAware(true);
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }

        return doc;
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
