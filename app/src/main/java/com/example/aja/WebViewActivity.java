package com.example.aja;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {
    String url;
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent i = getIntent();
        url = i.getStringExtra("url");

         webview = (WebView)findViewById(R.id.webview1);
        webview.getSettings().setJavaScriptEnabled(true);

        webview.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);
    }
}
