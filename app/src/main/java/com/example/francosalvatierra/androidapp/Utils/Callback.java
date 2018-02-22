package com.example.francosalvatierra.androidapp.Utils;

/**
 * Created by franco.salvatierra on 22/02/2018.
 */

public interface Callback {
    public void starting();
    public void completed(String res, int responseCode);
    public void completedWithErrors(Exception e);
    public void update();
}
