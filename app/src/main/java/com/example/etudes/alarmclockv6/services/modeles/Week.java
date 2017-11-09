package com.example.etudes.alarmclockv6.services.modeles;

import android.util.Log;
import android.util.TimeFormatException;

import com.example.etudes.alarmclockv6.services.WeekService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Florian on 02/11/2017.
 */

public class Week {

    private long id;
    private String monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    public Week(long id, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
        this.id = id;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public long getId() {
        return id;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
        WeekService.getInstance().updateWeek();
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
        WeekService.getInstance().updateWeek();
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
        WeekService.getInstance().updateWeek();
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
        WeekService.getInstance().updateWeek();
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
        WeekService.getInstance().updateWeek();
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
        WeekService.getInstance().updateWeek();
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
        WeekService.getInstance().updateWeek();
    }


    public String getADaysTime(Date date){
        String today = new SimpleDateFormat("EEEE").format(date);
        String time = "";
        switch (today){
            case "Monday":
            case "lundi":
                time = monday;
                break;
            case "Tuesday":
            case "mardi":
                time = tuesday;
                break;
            case "Thursday":
            case "jeudi":
                time =  thursday;
                break;
            case "Wednesday":
            case "mercredi":
                time = wednesday;
                break;
            case "Friday":
            case "vendredi":
                time =  friday;
                break;
            case "Saturday":
            case "samedi":
                time = saturday;
                break;
            case "Sunday":
            case "dimanche":
                time = sunday;
                break;
            default:
                time = "Error";
        }
        return time;
    }
}
