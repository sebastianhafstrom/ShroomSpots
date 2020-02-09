package se.umu.seha0054.shroomspots;

import java.util.Date;
import java.util.UUID;

public class ShroomSpot {
    private UUID ssID;
    private String title;
    private boolean emptied;
    private Date lastVisited;

    public ShroomSpot(){
        ssID = UUID.randomUUID();
        lastVisited = new Date();
    }

    public void setTitle(String s){
        title = s;
    }
    public String getTitle(){ return title; }
    public UUID getSSID(){
        return ssID;
    }

    public Date getLastVisited() {
        return lastVisited;
    }

    public void setLastVisited(Date lastVisited) {
        this.lastVisited = lastVisited;
    }

    public boolean isEmptied() {
        return emptied;
    }

    public void setEmptied(boolean emptied) {
        this.emptied = emptied;
    }
}
