package com.crogers.caltrainschedule;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by crogers on 8/24/17.
 */


public class BoardingCity {
    public BoardingCity(String nameI, ArrayList<DestinationCity> destinationsI){
        name = nameI;
        destinations = destinationsI;
    }
    public String name;
    public ArrayList<DestinationCity> destinations;
}