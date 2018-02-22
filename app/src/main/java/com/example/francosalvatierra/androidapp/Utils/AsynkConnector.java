package com.example.francosalvatierra.androidapp.Utils;

import android.os.AsyncTask;

/**
 * Created by franco.salvatierra on 22/02/2018.
 */

public class AsynkConnector extends AsyncTask <String, String, String> {

    private final String endpoint = "http://api.openweathermap.org/";

    private final String appendKey = "&APPID=3dd0766b0028a979d63ced881519b5b1";

    static final String COOKIES_HEADER = "Set-Cookie";

    public static final int WEATHER = 0;

    private static final String appendWeather = "data/2.5/weather";

    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";

    private Callback callback;
    private String content;
    private int service;

    private String param;

    private String url;
    private String method = GET;

    private int respondeCode;


}
