package com.example.francosalvatierra.androidapp.DataModel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by franco.salvatierra on 26/02/2018.
 */

public class WeatherDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WeatherDB.db";

    public WeatherDbHelper(Context context, String DATABASE_NAME, SQLiteDatabase.CursorFactory factory, int DATABASE_VERSION)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WeatherContract.WeatherEntry.CREAR_TABLA_WEATHER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
