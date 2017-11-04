package com.example.etudes.alarmclockv6;

/**
 * Created by Florian on 01/11/2017.
 */

public class Night {
    private long id;
    private String GoToBedEstimated, GotToBedReal, WakeUpEstimated, WakeUpReal;
    private boolean sleepWell;

    public Night(long id, String gtbe, String gtbr, String wue, String wur, boolean sleptWell) {
        this.id = id;
        this.GoToBedEstimated = gtbe;
        this.GotToBedReal= gtbr;
        this.WakeUpEstimated = wue;
        this.WakeUpReal= wur;
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
}
