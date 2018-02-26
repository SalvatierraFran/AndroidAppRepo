package com.example.francosalvatierra.androidapp;

//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Fragment currentFrag = null;
    FragmentTransaction ft = null;
    public static long lastUpdateTime = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    currentFrag = new HomeFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.miContainer, currentFrag);
                    ft.commit();
                    return true;

                case R.id.navigation_map:

                    currentFrag = new MapFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.miContainer, currentFrag);
                    ft.commit();
                    return true;

                case R.id.navigation_google:

                    currentFrag = new GoogleFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.miContainer, currentFrag);
                    ft.commit();
                    return true;

                case R.id.navigation_weather:

                    currentFrag = new WeatherFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.miContainer, currentFrag);
                    ft.commit();
                    return true;

                case R.id.navigation_database:

                    currentFrag = new DbFragment();
                    ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.miContainer, currentFrag);
                    ft.replace(R.id.miContainer, currentFrag);
                    ft.commit();
                    return true;
            }

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getSupportFragmentManager().beginTransaction();
        currentFrag = new HomeFragment();
        ft.replace(R.id.miContainer, currentFrag);
        ft.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
