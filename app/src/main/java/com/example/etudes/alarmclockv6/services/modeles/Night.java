package com.example.etudes.alarmclockv6.services.modeles;

/**
 * Created by Florian on 01/11/2017.
 */

public class Night {
    private long id;
    private String GoToBedEstimated, GotToBedReal, WakeUpEstimated, WakeUpReal;
    private boolean sleepWell, wasLate;
    public static final String DATE_FORMAT = "yyyy/MM/dd";
    public static final String HOUR_FORMAT = "HH:mm";
    public static final String DATE_HOUR_FORMAT = DATE_FORMAT+"-"+HOUR_FORMAT;

    public Night(long id, String goToBedEstimated, String gotToBedReal, String wakeUpEstimated, String wakeUpReal, boolean sleepWell, boolean wasLate) {
        this.id = id;
        GoToBedEstimated = goToBedEstimated;
        GotToBedReal = gotToBedReal;
        WakeUpEstimated = wakeUpEstimated;
        WakeUpReal = wakeUpReal;
        this.sleepWell = sleepWell;
        this.wasLate = wasLate;
    }

    public long getId() {
        return id;
    }

    public String getGoToBedEstimated() {
        return GoToBedEstimated;
    }

    public void setGoToBedEstimated(String goToBedEstimated) {
        GoToBedEstimated = goToBedEstimated;
    }

    public String getGotToBedReal() {
        return GotToBedReal;
    }

    public void setGotToBedReal(String gotToBedReal) {
        GotToBedReal = gotToBedReal;
    }

    public String getWakeUpEstimated() {
        return WakeUpEstimated;
    }

    public void setWakeUpEstimated(String wakeUpEstimated) {
        WakeUpEstimated = wakeUpEstimated;
    }

    public String getWakeUpReal() {
        return WakeUpReal;
    }

    public void setWakeUpReal(String wakeUpReal) {
        WakeUpReal = wakeUpReal;
    }

    public boolean isSleepWell() {
        return sleepWell;
    }

    public void setSleepWell(boolean sleepWell) {
        this.sleepWell = sleepWell;
    }

    public boolean isWasLate() {
        return wasLate;
    }

    public void setWasLate(boolean wasLate) {
        this.wasLate = wasLate;
    }

    public String toString(){
        return "ID : "+id+"; \n\t ESTIMATIONS(FA/WU) : "+GoToBedEstimated+"\t"+WakeUpEstimated+";\n\t REAL(FA/WU) : "+GotToBedReal+"\t"+WakeUpReal+";\n\t SLEPT WELL : "+sleepWell;
    }
}
