package com.example.francosalvatierra.androidapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.francosalvatierra.androidapp.Utils.AsynkConnector;
import com.example.francosalvatierra.androidapp.Utils.Callback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


public class WeatherFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getData();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    public void getData()
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
        }catch (JSONException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
