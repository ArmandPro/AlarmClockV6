package com.example.etudes.alarmclockv6;

import android.content.Context;


import com.example.etudes.alarmclockv6.Database.DatabaseManager;

import java.util.Random;

/**
 * Created by Florian on 02/11/2017.
 */

public class NightService {

    private DatabaseManager database;

    public NightService(Context context){
        database = new DatabaseManager(context);
    }

    public Night createNight(String wakeupEstimated){
        Night night = new Night((new Random()).nextLong(),"", "",wakeupEstimated, "",false);
        database.insertNight(night);
        return night;
    }
    public Night updateNight(String wakeupEstimated){
        Night night = new Night((new Random()).nextLong(),"", "",wakeupEstimated, "",false);
        database.updateNight(night);
        return night;
    }

    public boolean deleteNight(String date){
        return database.deleteNight(date);
    }

    public Night getNight(String date){
        return database.getNightByDate(date);
    }
}
