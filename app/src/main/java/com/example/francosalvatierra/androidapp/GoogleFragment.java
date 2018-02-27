package com.example.francosalvatierra.androidapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class GoogleFragment extends Fragment {

    public ProgressBar mProgressBar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =inflater.inflate(R.layout.fragment_google, container, false);

        WebView myWebView = (WebView)v.findViewById(R.id.google_wv);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://www.google.com.ar/");

        return v;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_google, container, false);
    }
}
