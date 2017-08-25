package com.crogers.caltrainschedule;

import java.util.ArrayList;

/**
 * Created by crogers on 8/24/17.
 */

public class DestinationCity {
    public DestinationCity(ArrayList<TrainStop> trainStopsI, String nameI){
        trainStops = trainStopsI;
        name = nameI;
    }
    public ArrayList<TrainStop> trainStops;
    public String name;

}
