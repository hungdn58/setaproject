package com.example.hoang.newsproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Random;

public class MyWebViewActivity extends AppCompatActivity {

    private WebView webView;
    private Button back, arr_back, share, arr_forward;
    private TextView title, link;
    private static int count = 0;
    private RSSItem item;
    private InterstitialAd interstitia;
    private int random = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);

        interstitia = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        interstitia.setAdUnitId(getString(R.string.aos_vnexpress_insteria));
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitia.loadAd(adRequest);
        interstitia.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

            }
        });

        webView = (WebView) findViewById(R.id.webview);
        back = (Button) findViewById(R.id.back);
        arr_back = (Button) findViewById(R.id.goback);
        share = (Button) findViewById(R.id.share);
        arr_forward = (Button) findViewById(R.id.goforward);

        Typeface font = FontManager.getTypeface(getApplicationContext(), FontManager.FONTAWESOME);
        back.setTypeface(font);
        share.setTypeface(font);
        arr_back.setTypeface(font);
        arr_forward.setTypeface(font);
        title = (TextView) findViewById(R.id.mtitle);
        link = (TextView) findViewById(R.id.link);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        arr_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goBack();
            }
        });

        arr_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goForward();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Great Artical" + item.getTitle();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, item.get_link());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        final ProgressDialog progressDialog = new ProgressDialog(MyWebViewActivity.this);
        progressDialog.setMessage("Loading ...");

        progressDialog.show();

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.hide();
                if (item.getCount() == 1) {
                    if (interstitia != null && interstitia.isLoaded()) {
                        interstitia.show();
                    }
                    Random rd = new Random();
                    random = rd.nextInt(11);
                    while (random == 0 || random == 1) {
                        random = rd.nextInt(11);
                    }
                    MainActivity.setDefault("random", random, MyWebViewActivity.this);
                } else if (item.getCount() % 10 == MainActivity.getDefaults("random", MyWebViewActivity.this)) {
                    if (interstitia != null && interstitia.isLoaded()) {
                        interstitia.show();
                    }
                }
                AdView mAdView = (AdView) findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder().build();
                mAdView.loadAd(adRequest);
            }

//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                if (!interstitia.isLoading() && !interstitia.isLoaded()) {
//
//                }
//                return super.shouldOverrideUrlLoading(view, url);
//            }
        });

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.getSettings().setDisplayZoomControls(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        item = (RSSItem) getIntent().getSerializableExtra("item");
        if (item.getCount() % 10 == 0){
            Random rd = new Random();
            while (random == 0 || random == 1) {
                random = rd.nextInt(11);
            }
            MainActivity.setDefault("random", random, MyWebViewActivity.this);
            Log.d("RANDOM", MainActivity.getDefaults("random", MyWebViewActivity.this) + "");
        }

        title.setText(item.getTitle());
        link.setText(item.get_source());
        webView.loadUrl(item.get_link());
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
