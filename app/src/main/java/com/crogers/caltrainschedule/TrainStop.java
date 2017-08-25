package com.crogers.caltrainschedule;

/**
 * Created by crogers on 8/24/17.
 */

public class TrainStop {
    public TrainStop(Integer trainIdI, String fromI, String toI){
        trainId = trainIdI;
        from = fromI;
        to = toI;
    }
    public Integer trainId;
    public String from; // Time of arrival city
    public String to; // Time of desitnation city
}
