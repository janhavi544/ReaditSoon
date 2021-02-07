package com.readitsoon.pabbas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Webview extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    boolean loadingFinished = true;
    boolean redirect = false;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        String url=getIntent().getStringExtra("url");
        webView = (WebView)findViewById(R.id.webView);
        toolbar=(Toolbar)findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageCommitVisible (WebView view,
                                             String url){
                progressBar.setVisibility(View.GONE);
            }
        });

        if(url!=null)
        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
    public void onBackPressed(){
        if (webView.canGoBack()){
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}