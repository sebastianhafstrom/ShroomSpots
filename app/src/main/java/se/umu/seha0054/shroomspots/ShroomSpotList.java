package se.umu.seha0054.shroomspots;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShroomSpotList {
    private static ShroomSpotList shroomSpotList;
    private List<ShroomSpot> shroomSpots;

    public static ShroomSpotList get(Context context){
        if(shroomSpotList == null){
            shroomSpotList = new ShroomSpotList(context);
        }
        return shroomSpotList;
    }

    public void add(ShroomSpot s){
        shroomSpots.add(s);
    }

    private ShroomSpotList(Context context){
        shroomSpots = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            ShroomSpot s = new ShroomSpot();
            s.setTitle("ShroomSpot #" + i);
            shroomSpots.add(s);
        }
    }

    public List<ShroomSpot> getShroomSpots() {
        return shroomSpots;
    }

    public ShroomSpot getShroomSpot(UUID id){
        for( ShroomSpot s : shroomSpots){
            if(s.getSSID().equals(id)){
                return s;
            }
        }
        return null;
    }
}
