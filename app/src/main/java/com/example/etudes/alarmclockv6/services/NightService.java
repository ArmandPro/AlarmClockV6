package com.example.etudes.alarmclockv6.services;

import android.content.Context;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.services.modeles.Night;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Florian on 02/11/2017.
 * Provides nights functionnalities and data access
 */


public class NightService {

    private DatabaseManager database;
    private static NightService instance = null;
    private static long lastId = -1;

    private NightService(Context context) {
        database = DatabaseManager.getInstance(context);
        lastId = database.getLastNightId();
        if( lastId<0)lastId=0;

    }

    public static NightService getInstance() {
        if (instance == null) {
            instance = new NightService(null);
        }
        return instance;
    }

    public Night createNight() {
        Calendar.getInstance().add(Calendar.DAY_OF_YEAR, 1);
        String wue = new SimpleDateFormat(Night.DATE_FORMAT).format(Calendar.getInstance().getTime()) + "-" + WeekService.getInstance().getWeek().getADaysTime(Calendar.getInstance().getTime());
        Night night = new Night(lastId+=1, "", "", wue, "", false, false);
        night = estimateNight(night);
        database.insertNight(night);
        return night;
    }



    public Night createNight(String date) {
        Date day = null;
        try {
            day = new SimpleDateFormat(Night.DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String wue = date+ "-" + WeekService.getInstance().getWeek().getADaysTime(day);
        Date today = Calendar.getInstance().getTime();
        Night night = new Night(lastId+=1, "", "", wue, "", false, false);
        night = estimateNight(night);
        database.insertNight(night);
        return night;

    }

    public Night wasLate(){
        String day = new SimpleDateFormat(Night.DATE_FORMAT).format(new Date());
        Night night = getNight(day);
        night.setWasLate(true);
        return updateNight(night);
    }


    private Night updateNight(Night night) {
        database.updateNight(night);
        return night;
    }

    public Night wokeUp() {
        String wakeUpReal = new SimpleDateFormat(Night.DATE_HOUR_FORMAT).format(new Date());
        String date = wakeUpReal.split("-")[0];
        Night night = getNight(date);
        if(night !=null) {
            night.setWakeUpReal(wakeUpReal);
            HabitsService.getInstance().getHabits().incrementDaysOfUse();
            return updateNight(night);
        }
        return null;
    }

    public Night fellAsleep() {
        Calendar calendar = Calendar.getInstance();
        String goToBedReal = new SimpleDateFormat(Night.DATE_HOUR_FORMAT).format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String date = new SimpleDateFormat(Night.DATE_FORMAT).format(calendar.getTime());
        Night night = getNight(date);
        night.setGotToBedReal(goToBedReal);
        return updateNight(night);
    }

    private Night estimateNight(Night night) {
        try {
            Calendar calendar = Calendar.getInstance();
            Date wakeUp = new SimpleDateFormat(Night.DATE_HOUR_FORMAT).parse(night.getWakeUpEstimated());
            calendar.setTime(wakeUp);
            calendar.add(Calendar.HOUR, -1 * HabitsService.getInstance().getHabits().getSleepHoursPerNight());
            night.setGoToBedEstimated(new SimpleDateFormat(Night.DATE_HOUR_FORMAT).format(calendar.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return updateNight(night);
    }


    /*
     * Returns the night once modified, but only works if the night has not been passed
     */
    public Night updateCurrentNight(String time) {
       Night night = database.getNightByDate(new SimpleDateFormat(Night.DATE_FORMAT).format(new Date()));
        if (night != null) {
            if (!night.getWakeUpReal().equals("none")) {
                return night;
            }
            Calendar.getInstance().add(Calendar.DAY_OF_YEAR, 1);
            String wakeUpEstimated = new SimpleDateFormat(Night.DATE_FORMAT).format(Calendar.getInstance().getTime()) + "-" + time;
            night.setWakeUpEstimated(wakeUpEstimated);
            return estimateNight(night);
        } else {
            return createNight();
        }
    }

    /*
     * Return the next night updated, if it didn't exist, it is created
     */
    public Night updateNextNight(String time) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String date = new SimpleDateFormat(Night.DATE_FORMAT).format(calendar.getTime());
        Night night = database.getNightByDate(date);
        String wakeUpEstimated = date + "-" + time;
        if (night != null) {
            night.setWakeUpEstimated(wakeUpEstimated);
            return estimateNight(night);
        } else {
            return createNight(date);
        }
    }

    /*
     * returns the lateness of a sleep, if negative, it means it was early
     */
    public int calculateNightLateness(Night night){
        try {
            Date estimated = new SimpleDateFormat(Night.DATE_HOUR_FORMAT).parse(night.getWakeUpEstimated());
            Date real = new SimpleDateFormat(Night.DATE_HOUR_FORMAT).parse(night.getWakeUpReal());
            return (int)(real.getTime()-estimated.getTime())/(1000*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public Night getLastWeekNight(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR,-7);
        return NightService.getInstance().getNight(new SimpleDateFormat(Night.DATE_FORMAT).format(calendar.getTime()));
    }

    public Night getNight(String date) {
        return database.getNightByDate(date);
    }


}
