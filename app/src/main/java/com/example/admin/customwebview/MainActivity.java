package com.example.admin.customwebview;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ListView;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    MyWebView myWebView;
    ImageView info;
    ListView webPages;
    String[] names;
    Integer[] icons={R.mipmap.ic_google,
            R.mipmap.ic_youtube,
            R.mipmap.ic_gmail};

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (ImageView) findViewById(R.id.info);
        webPages=(ListView)findViewById(R.id.web_page);

        names = getResources().getStringArray(R.array.name);
        //icons = getResources().getIntArray(R.array.icon);

        MyListAdapter adapter=new MyListAdapter(this, names, icons);
        webPages.setAdapter(adapter);

        final FlowingDrawer mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    Log.i("MainActivity", "Drawer STATE_CLOSET");
                }
                if (newState == ElasticDrawer.STATE_OPEN) {
                    Log.i("MainActivity", "Drawer STATE_OPEN");
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openMenu(true);
            }
        });

        myWebView = (MyWebView) findViewById(R.id.web_view);
        myWebView.loadUrl("https://www.google.com/");

        /*webView = (WebView) findViewById(R.id.web_view);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.google.com/");*/
        /*webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });*/

    }
}
