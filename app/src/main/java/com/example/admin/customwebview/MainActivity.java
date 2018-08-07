package com.example.admin.customwebview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

public class MainActivity extends AppCompatActivity {
    //WebView webView;
    MyWebView myWebView;
    ImageView info;
    FloatingActionButton sendEmailBtn;
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
        sendEmailBtn = (FloatingActionButton) findViewById(R.id.send_email);
        webPages=(ListView)findViewById(R.id.web_page);

        names = getResources().getStringArray(R.array.name);
        //icons = getResources().getIntArray(R.array.icon);

        MyListAdapter adapter = new MyListAdapter(this, names, icons);
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
        myWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webPages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        myWebView.loadUrl("https://www.google.com/");
                        mDrawer.closeMenu(true);
                        break;
                    case 1:
                        myWebView.loadUrl("https://www.youtube.com/");
                        mDrawer.closeMenu(true);
                        break;
                    case 2:
                        myWebView.loadUrl("https://www.google.com/intl/ru/gmail/about/");
                        mDrawer.closeMenu(true);
                        break;

                        default:
                            mDrawer.closeMenu(true);
                            break;
                }
            }
        });
       sendEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    //Складіть електронний лист із додатковими вкладеннями
    //    Каждое письмо E-mail содержит две основные части: заголовок и тело.
    //Заголовок письма
    //В поле To: (Куда:, Кому:) записывается адрес получателя сообщения. В зависимости от используемого сервера или почтовой клиент-программы это поле может называться Message To: или Mail To:
    //Поле From: Твой адрес E-mail. Обычно этот адрес автоматически записывается и поэтому может отсутствовать на экране.
    //В поле Subject: (Тема:) указывается краткая аннотация содержания сообщения (не более 20–30 знаков)
    //В поле СC: (Копия:) при необходимости записываются адреса корреспондентов, которым рассылаются копии сообщений.
    //В поле ВСC: (Скрытая копия:) указываются адреса корреспондентов, которым копии сообщений рассылаются в тайне от первого адресата.
    //Дата и время отправки автоматически включается в заголовок сообщения клиент-программой.
    public void sendEmail(){
        Log.i("Send email", "");
        String[] TO = {"denia.pitsul@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));//Потрібно лише обробляти це електронною поштою
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);//адрес електронної пошти одержувача "Кому".
        emailIntent.putExtra(Intent.EXTRA_CC, CC);//адрес електронної пошти одержувача "CC".
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");//Строка з темою електронної пошти.
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");//Строка з тілом електронної пошти.

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
