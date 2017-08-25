package com.crogers.caltrainschedule;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by crogers on 8/24/17.
 */

public class ScheduleDataStore {

    public ScheduleDataStore(){
        //FileReader in = new FileReader("trips.json");
        //JsonReader reader = new JsonReader(in);

    }

    private List<String> getCities(){
        return new ArrayList<String>(Arrays.asList("San Francisco", "San Mateo"));
    }

    public ArrayAdapter getArrivingAdapter(Activity delegate){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(delegate, android.R.layout.simple_spinner_item, getCities());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public ArrayAdapter getDepartingAdapter(Activity delegate){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(delegate, android.R.layout.simple_spinner_item, getCities());

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public String getTimeDeltas(String arrival, String departure){
        if(arrival == departure){
            return "Cities must differ";
        }

        return "6:01PM -> 10:11PM\n7:02PM -> 11:00PM";
    }

}
