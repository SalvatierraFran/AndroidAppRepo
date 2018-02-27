package com.example.francosalvatierra.androidapp.DataModel;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.francosalvatierra.androidapp.Entities.WeatherData;
import com.example.francosalvatierra.androidapp.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by franco.salvatierra on 27/02/2018.
 */

public class WeatherAdapter extends ArrayAdapter<WeatherData> {
    List<WeatherData> lista;
    Activity context;

    public WeatherAdapter(@NonNull Context context, int resource, List<WeatherData> lista)
    {
        super(context, resource, lista);

        this.lista = lista;
        this.context = (Activity)context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater lnf = context.getLayoutInflater();
        View row = lnf.inflate(R.layout.row_layout, null);

        TextView tvCiudad = (TextView)row.findViewById(R.id.dbCiudad_tv);
        tvCiudad.setText(lista.get(position).getCiudad());

        TextView tvDesc = (TextView)row.findViewById(R.id.dbDesc_tv);
        tvDesc.setText(lista.get(position).getDesc());

        return row;
    }
}
