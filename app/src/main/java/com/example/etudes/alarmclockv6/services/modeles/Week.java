package com.example.etudes.alarmclockv6.services.modeles;

import com.example.etudes.alarmclockv6.services.WeekService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    public void setXdayTime(int index, String time){
        switch (index){
            case 0:
                setMonday(time);
                break;
            case 1:
                setTuesday(time);
                break;
            case 2:
                setWednesday(time);
                break;
            case 3:
                setThursday(time);
                break;
            case 4:
                setFriday(time);
                break;
            case 5:
                setSaturday(time);
                break;
            case 6:
                setSunday(time);
                break;
            default:
                break;
        }
    }

    public String getXdayTime(int index){
        switch (index){
            case 0:
                return getMonday();
            case 1:
                return getTuesday();
            case 2:
                return getWednesday();
            case 3:
                return getThursday();
            case 4:
                return getFriday();
            case 5:
                return getSaturday();
            case 6:
                return getSunday();
            default:
                return "none";
        }
    }

    public String getADaysTime(Date date){
        String today = new SimpleDateFormat("EEEEE", Locale.ENGLISH).format(date);
        String time = "";
        switch (today){
            case "Monday":
                time = monday;
                break;
            case "Tuesday":
                time = tuesday;
                break;
            case "Thursday":
                time =  thursday;
                break;
            case "Wednesday":
                time = wednesday;
                break;
            case "Friday":
                time =  friday;
                break;
            case "Saturday":
                time = saturday;
                break;
            case "Sunday":
                time = sunday;
                break;
            default:
                time = "none";
        }
        return time;
    }
}
