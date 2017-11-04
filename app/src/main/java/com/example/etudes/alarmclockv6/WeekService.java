package com.example.etudes.alarmclockv6;

import android.content.Context;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;

/**
 * Created by Florian on 02/11/2017.
 */

public class WeekService {

    private DatabaseManager database;

    public WeekService(Context context){
        database = new DatabaseManager(context);
    }


    public Week createWeek(String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday){
        Week week = new Week(1, monday,tuesday,wednesday,thursday,friday,saturday,sunday);
        database.insertWeek(week);
        return week;
    }


    public Week updateWeek(String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday){
        Week week = new Week(1, monday,tuesday,wednesday,thursday,friday,saturday,sunday);
        database.updateWeek(week);
        return week;
    }

    public boolean deleteWeek(Week week){
        return database.deleteWeek(week.getId());
    }

    public Week getWeek(long id){
        return database.getWeek(id);
    }

}
