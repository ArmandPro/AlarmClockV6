package com.example.etudes.alarmclockv6.services;

import android.content.Context;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.services.modeles.Night;
import com.example.etudes.alarmclockv6.services.modeles.Week;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Florian on 02/11/2017.
 */

public class WeekService {

    private DatabaseManager database;
    private static WeekService instance = null;
    public static Week week = null;

    private WeekService(Context context){
        database = DatabaseManager.getInstance(context);
        if((week = getWeek())==null){
                String defaultTime = "07:00";
                week = createWeek(defaultTime,defaultTime,defaultTime,defaultTime,defaultTime,defaultTime,defaultTime);
        }
    }

    public static WeekService getInstance(){
        if(instance == null){
            instance = new WeekService(null);
        }
        return instance;
    }

    private Week createWeek(String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday){
        Week week = new Week(new Random().nextLong(), monday,tuesday,wednesday,thursday,friday,saturday,sunday);
        database.insertWeek(week);
        return week;
    }


    public Week updateWeek(){
        database.updateWeek(week);
        NightService nightService = NightService.getInstance();
        nightService.updateCurrentNight(week.getADaysTime(new Date()));
        Calendar.getInstance().add(Calendar.DAY_OF_YEAR,1);
        nightService.updateNextNight(week.getADaysTime(Calendar.getInstance().getTime()));
        return week;
    }


    public boolean deleteWeek(Week week){
        return database.deleteWeek(week.getId());
    }

    public Week getWeek(){
        if(week == null) {
            week = database.getWeek();
        }
        return week;
    }



}
