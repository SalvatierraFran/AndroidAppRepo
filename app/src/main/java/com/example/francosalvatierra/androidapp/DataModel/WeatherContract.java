package com.example.francosalvatierra.androidapp.DataModel;

import android.provider.BaseColumns;

/**
 * Created by franco.salvatierra on 26/02/2018.
 */

public class WeatherContract {

    public static abstract class WeatherEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "Weather";

        public static final String CAMPO_CIUDAD = "ciudad";

        public static final String CAMPO_DESC = "descripcion";

        public static final String CAMPO_TEMP = "temperatura";

        public static final String CAMPO_TEMPMIN = "min";

        public static final String CAMPO_TEMPMAX = "max";

        public static final String CAMPO_HUMEDAD = "humedad";

        public static final String CREAR_TABLA_WEATHER = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY " +
                "KEY AUTOINCREMENT," + CAMPO_CIUDAD + " TEXT NOT NULL," + CAMPO_DESC + " TEXT NOT NULL," + CAMPO_TEMP +
                " TEXT NOT NULL," + CAMPO_TEMPMIN + " TEXT NOT NULL," + CAMPO_TEMPMAX + " TEXT NOT NULL," + CAMPO_HUMEDAD +
                " TEXT NOT NULL)";
    }
}
