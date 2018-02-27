package com.example.francosalvatierra.androidapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.francosalvatierra.androidapp.DataModel.WeatherContract;
import com.example.francosalvatierra.androidapp.DataModel.WeatherDbHelper;
import com.example.francosalvatierra.androidapp.Entities.WeatherData;
import com.example.francosalvatierra.androidapp.Utils.AsynkConnector;
import com.example.francosalvatierra.androidapp.Utils.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;

import java.util.ArrayList;


public class WeatherFragment extends Fragment {
    WeatherData data;
    ArrayList<WeatherData> lista = new ArrayList<WeatherData>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(GuardarHora()){
            getDataFromService();
        }else{
            getDataFromDB();
        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    private void getDataFromDB() {
        WeatherDbHelper conn = new WeatherDbHelper(this.getContext(), "WeatherDB", null, WeatherDbHelper.DATABASE_VERSION);

        SQLiteDatabase db = conn.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM Weather", null);

        if(c.moveToLast())
        {
            do{
                data = new WeatherData(c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6));
                lista.add(data);
            }while(c.moveToNext());
        }
    }

    public void getDataFromService()
    {
        String content = "{}";

        String param = "?id=3435910";

        AsynkConnector c = new AsynkConnector(AsynkConnector.WEATHER, param, content, new Callback(){
            private int dots = 0;

            @Override
            public void starting() {

            }

            @Override
            public void completed(String res, int responseCode) {
                if(responseCode == 200)
                {
                    parseResponseJSON(res);
                }
            }

            @Override
            public void completedWithErrors(Exception e) {

            }

            @Override
            public void update() {

            }
        });
        c.execute();
    }

    private void parseResponseJSON(String res)
    {
        try
        {
            System.out.println(res);

            JSONObject reader = new JSONObject(res);

            JSONObject d = reader.getJSONObject("coord");

            d = reader.getJSONObject("main");

            TextView name = (TextView)this.getActivity().findViewById(R.id.weather_name);
            name.setText(reader.getString("name"));

            TextView temp = (TextView)this.getActivity().findViewById(R.id.weather_temp);
            temp.setText(d.getString("temp"));

            TextView tempmin = (TextView)this.getActivity().findViewById(R.id.weather_tempmin);
            tempmin.setText(d.getString("temp_min"));

            TextView tempmax = (TextView)this.getActivity().findViewById(R.id.weather_tempmax);
            tempmax.setText(d.getString("temp_max"));

            TextView humidity = (TextView)this.getActivity().findViewById(R.id.weather_humidity);
            humidity.setText(d.getString("humidity"));

            JSONArray w = reader.getJSONArray("weather");

            TextView desc = (TextView)this.getActivity().findViewById(R.id.weather_desc);
            desc.setText(w.getJSONObject(0).getString("description"));

            GuardarHora();

            WeatherDbHelper conn = new WeatherDbHelper(this.getContext(), "WeatherDB", null, WeatherDbHelper.DATABASE_VERSION);

            SQLiteDatabase db = conn.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(WeatherContract.WeatherEntry.CAMPO_CIUDAD, reader.getString("name"));
            values.put(WeatherContract.WeatherEntry.CAMPO_DESC, w.getJSONObject(0).getString("description"));
            values.put(WeatherContract.WeatherEntry.CAMPO_HUMEDAD, d.getString("humidity"));
            values.put(WeatherContract.WeatherEntry.CAMPO_TEMP, d.getString("temp"));
            values.put(WeatherContract.WeatherEntry.CAMPO_TEMPMIN, d.getString("temp_min"));
            values.put(WeatherContract.WeatherEntry.CAMPO_TEMPMAX, d.getString("temp_max"));

            Long idResultante = db.insert(WeatherContract.WeatherEntry.TABLE_NAME, WeatherContract.WeatherEntry._ID, values);

            Toast.makeText(this.getActivity().getApplicationContext(), "Id Registro: "+ idResultante, Toast.LENGTH_SHORT).show();

        }catch (JSONException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public boolean GuardarHora()
    {
        int delta = 30 *60*1000;
        long currentTime = System.currentTimeMillis();

        if(currentTime-MainActivity.lastUpdateTime >= delta){
            MainActivity.lastUpdateTime=currentTime;
            return true;
        }
        return false;

    }
}
