package com.example.hoang.newsproject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.example.hoang.newsproject.image.AdvViewPager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.viewpagerindicator.PageIndicator;

import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AsyncResponse, ViewPager.OnPageChangeListener, SwipeRefreshLayout.OnRefreshListener{

    private ViewPager viewpager;
    private PageIndicator titlePagerIndicator, linePagerIndicator;
    private RSSFeed feed = null;
    private ArrayList<RSSFeed> arrFeeds = new ArrayList<RSSFeed>();
    private ArrayList<String> arrTitles = new ArrayList<String>();
    private ArrayList<String> arrUrls = new ArrayList<String>();
    private Fragment fragment = null;
    private static int i = 0;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout splash;
    private ArrayList<CategoryFragment> arrFragments = new ArrayList<CategoryFragment>();

    private ViewPager viewPager;
    private AdvViewPager myPagerAdapter;
    private PageIndicator mIndicator;
    private RelativeLayout homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        getControls();


//        setUpViewPager(viewPager);
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() == null) {

            splash.setVisibility(View.VISIBLE);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(
                    "Unable to reach server, \nPlease check your connectivity.")
                    .setTitle("NEWS'S NOTIFICATION")
                    .setCancelable(false)
                    .setPositiveButton("Exit",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    finish();
                                }
                            });

            AlertDialog alert = builder.create();
            alert.show();
        }
        homeButton = (RelativeLayout) findViewById(R.id.home_button);
        homeButton.setVisibility(View.INVISIBLE);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
    }

    private void getControls(){

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        titlePagerIndicator = (PageIndicator) findViewById(R.id.pagerindicator);
        titlePagerIndicator.setOnPageChangeListener(this);
//        linePagerIndicator = (PageIndicator) findViewById(R.id.linePagerIndicator);
        setUpViewPager(viewPager);
//        linePagerIndicator.setViewPager(viewPager);
        titlePagerIndicator.setViewPager(viewPager);

//        viewpager = (ViewPager) findViewById(R.id.viewpager);
//        myPagerAdapter = new AdvViewPager(this);
//        viewpager.setAdapter(myPagerAdapter);
//        mIndicator = (PageIndicator) findViewById(R.id.adPagerindicator);
//        mIndicator.setViewPager(viewpager);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        splash = (RelativeLayout) findViewById(R.id.splash);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe2refresh);

        swipeRefreshLayout.setOnRefreshListener(this);

//        swipeRefreshLayout.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        swipeRefreshLayout.setRefreshing(true);
//
//                                        AsyncLoadXMLOneFeed gj = new AsyncLoadXMLOneFeed();
//                                        gj.execute(arrUrls.get(viewPager.getCurrentItem()));
//                                    }
//                                }
//        );

//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setMessage("Loading ...");
//
//        progressDialog.show();


        arrUrls.add("http://www.24h.com.vn/upload/rss/tintuctrongngay.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/bongda.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/anninhhinhsu.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/thoitrang.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/thoitranghitech.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/taichinhbatdongsan.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/amthuc.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/lamdep.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/phim.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/giaoducduhoc.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/bantrecuocsong.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/canhacmtv.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/thethao.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/phithuongkyquac.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/congnghethongtin.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/otoxemay.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/thitruongtieudung.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/dulich.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/suckhoedoisong.rss");
        arrUrls.add("http://www.24h.com.vn/upload/rss/cuoi24h.rss");

        viewPager.setOffscreenPageLimit(arrUrls.size());


        arrTitles.add("Trang Chủ");
        arrTitles.add("Bóng Đá");
        arrTitles.add("An Ninh");
        arrTitles.add("Thời Trang");
        arrTitles.add("Công Nghệ");
        arrTitles.add("Tài Chính");
        arrTitles.add("Ẩm Thực");
        arrTitles.add("Làm Đẹp");
        arrTitles.add("Phim");
        arrTitles.add("Giáo Dục");
        arrTitles.add("Nhịp Sống Trẻ");
        arrTitles.add("Ca Nhạc");
        arrTitles.add("Thể Thao");
        arrTitles.add("Phi Thường");
        arrTitles.add("Công Nghệ");
        arrTitles.add("Ô Tô");
        arrTitles.add("Thị Trường");
        arrTitles.add("Du Lịch");
        arrTitles.add("Sức Khỏe");
        arrTitles.add("Cười 24h");

        for (int i = 0; i < arrUrls.size(); i++){
            AsyncLoadXMLFeed gj = new AsyncLoadXMLFeed(this);
            gj.execute(arrUrls.get(i), arrTitles.get(i));
        }

    }

    private void fetchData(int page){
        AsyncLoadXMLFeed gj = new AsyncLoadXMLFeed(this);
        gj.execute(arrUrls.get(page));
    }

    private void setUpViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        SettingsFragment settingsFragment = new SettingsFragment();
        viewPagerAdapter.addFragment(settingsFragment, "Cài Đặt");
        for (int i = 0; i < arrFeeds.size(); i++){
            fragment = CategoryFragment.newInstance(arrFeeds.get(i), arrTitles.get(i));
            arrFragments.add((CategoryFragment) fragment);
            viewPagerAdapter.addFragment(fragment, arrTitles.get(i));
        }

        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void processFinish(RSSFeed output) {
        arrFeeds.add(output);
        i++;
        if (i == arrUrls.size()){
            setUpViewPager(viewPager);
//            progressDialog.hide();
            splash.setVisibility(View.GONE);
            homeButton.setVisibility(View.VISIBLE);
            viewPager.setCurrentItem(1);
            swipeRefreshLayout.setRefreshing(false);
        }
//        Toast.makeText(MainActivity.this, output.getItem(1).getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        viewPager = (ViewPager)findViewById(R.id.viewPager);
    }

    @Override
    public void onPageSelected(int position) {
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        if (position == 0){
            swipeRefreshLayout.setEnabled(false);
        }else {
            swipeRefreshLayout.setEnabled(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onRefresh() {
        AsyncLoadXMLOneFeed gj = new AsyncLoadXMLOneFeed();
        gj.execute(arrUrls.get(viewPager.getCurrentItem() - 1), arrTitles.get(viewPager.getCurrentItem() - 1));
    }

    private class AsyncLoadXMLFeed extends AsyncTask<String, Void, RSSFeed> {

        private AsyncResponse listener;

        public AsyncLoadXMLFeed(AsyncResponse listener){
            this.listener=listener;
        }

        @Override
        protected RSSFeed doInBackground(String... params) {
            // Obtain feed
            DOMParser myParser = new DOMParser(params[1]);
            feed = myParser.parseXml(params[0]);
            if (feed != null) {
//                Log.d("NEWSEXAMPLE", "khac null");
            }
            return feed;
        }

        @Override
        protected void onPostExecute(RSSFeed result) {
            super.onPostExecute(result);
            listener.processFinish(result);
        }
    }

    private class AsyncLoadXMLOneFeed extends AsyncTask<String, Void, RSSFeed> {

        @Override
        protected RSSFeed doInBackground(String... params) {
            // Obtain feed
            DOMParser myParser = new DOMParser(params[1]);
            feed = myParser.parseXml(params[0]);
            if (feed != null) {
//                Log.d("NEWSEXAMPLE", "khac null");
            }
            return feed;
        }

        @Override
        protected void onPostExecute(RSSFeed result) {
            super.onPostExecute(result);
            arrFragments.get(viewPager.getCurrentItem() - 1).upDateUI(result.get_itemlist());
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public static void setDefault(String key, int value, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, 0);
    }
}