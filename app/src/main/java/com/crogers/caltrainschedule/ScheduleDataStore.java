package com.crogers.caltrainschedule;

import android.app.Activity;
import android.content.Context;
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
    ArrayList<BoardingCity> boardingCities = new ArrayList<>();
    public ScheduleDataStore(InputStream is){
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
            reader.beginObject();
            while(reader.hasNext()){
                //name of boarding city
                String name = reader.nextName();
                BoardingCity city = readCity(reader, name);
                boardingCities.add(city);
            }
            reader.endObject();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public List<String> getCities(){
        ArrayList<String> cityNames = new ArrayList<>();
        for (BoardingCity city: boardingCities) {
                cityNames.add(city.name);
        }
        return cityNames;
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
        String baseString = "";
        for(BoardingCity city: boardingCities){
            if(city.name.contains(arrival)){
                for(DestinationCity dest: city.destinations){
                    if(dest.name.contains(departure)){
                        for(TrainStop stop: dest.trainStops){
                            baseString += "\n" + stop.from + " -> " + stop.to;
                        }
                    }
                }
            }
        }
        return baseString;
    }

    private BoardingCity readCity(JsonReader reader, String name) throws IOException{
        ArrayList<DestinationCity> destinations= new ArrayList<>();
        try{
        reader.beginObject();
        while(reader.hasNext()){
            //name of destination city
            String dn = reader.nextName();
            DestinationCity city = readDestination(reader, dn);
            destinations.add(city);
        }
        reader.endObject();
        } catch(IOException ex) {}
        return new BoardingCity(name, destinations);
    }
    private DestinationCity readDestination(JsonReader reader, String name) throws IOException{
        ArrayList<TrainStop>  trains = new ArrayList<>();
        try{
        reader.beginArray();
        while(reader.hasNext()){
            //name of boarding city
            TrainStop train  = readTrainStop(reader);
            trains.add(train);
        }
        reader.endArray();
        } catch(IOException ex) {}
        return new DestinationCity(trains, name);
    }
    private TrainStop readTrainStop(JsonReader reader) throws IOException{
        ArrayList<TrainStop> trains;
        reader.beginObject();
        Integer trainId = 0;
        String from = "";
        String to = "";
        try {
            while (reader.hasNext()) {
                //name of boarding city
                String name = reader.nextName();
                if (name.contains("TrainId")) {
                    trainId = reader.nextInt();
                }
                if (name.contains("Arrive")) {
                    to = reader.nextString();
                }
                if (name.contains("Depart")) {
                    from = reader.nextString();
                }
            }
            reader.endObject();
        } catch(IOException ex) {}

        return new TrainStop(trainId, from, to);
    }
}
