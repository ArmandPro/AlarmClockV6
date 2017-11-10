package com.example.etudes.alarmclockv6.services;

import android.content.Context;
import android.util.Log;


import com.example.etudes.alarmclockv6.Database.DatabaseConstants;
import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.services.modeles.Night;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Florian on 02/11/2017.
 */

public class NightService {

    private DatabaseManager database;
    private static NightService instance = null;

    private NightService(Context context){
        database = DatabaseManager.getInstance(context);
    }

    public static NightService getInstance(){
        if(instance==null){
            instance = new NightService(null);
        }
        return instance;
    }

    public Night createNight(){
        Calendar.getInstance().add(Calendar.DAY_OF_YEAR,1);
        String wue = new SimpleDateFormat(Night.DATE_FORMAT).format(Calendar.getInstance().getTime())+"-"+WeekService.getInstance().getWeek().getADaysTime(Calendar.getInstance().getTime());
        Night night = new Night(HabitsService.getInstance().getHabits().getDaysOfUse()+1,"", "",wue, "",false);
        night = estimateNight(night);
        database.insertNight(night);
        return night;
    }

    private Night updateNight(Night night){
        database.updateNight(night);
        return night;
    }

    public Night wokeUp(){
        String wakeUpReal = new SimpleDateFormat(Night.DATE_HOUR_FORMAT).format(new Date());
        String date = wakeUpReal.split("-")[0];
        Night night = getNight(date);
        night.setWakeUpReal(wakeUpReal);
        HabitsService.getInstance().getHabits().incrementDaysOfUse();
        return updateNight(night);
    }

    public Night fellAsleep(){
        String goToBedReal= new SimpleDateFormat(Night.DATE_HOUR_FORMAT).format(Calendar.getInstance().getTime());
        Calendar.getInstance().add(Calendar.DAY_OF_YEAR,1);
        String date = new SimpleDateFormat(Night.DATE_FORMAT).format(Calendar.getInstance().getTime());
        Night night = getNight(date);
        night.setGotToBedReal(goToBedReal);
        return updateNight(night);
    }

    private Night estimateNight(Night night){
        try {
            Date wakeUp = new SimpleDateFormat(Night.DATE_HOUR_FORMAT).parse(night.getWakeUpEstimated());
            Calendar.getInstance().setTime(wakeUp);
            Calendar.getInstance().add(Calendar.HOUR_OF_DAY,-1*HabitsService.getInstance().getHabits().getSleepHoursPerNight());
            night.setGoToBedEstimated(new SimpleDateFormat(Night.DATE_HOUR_FORMAT).format(Calendar.getInstance().getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return updateNight(night);
    }


    /*
     * Returns the night once modified, but only works if the night has not been passed
     */
    public Night updateCurrentNight(String time){
        long id = (long) HabitsService.getInstance().getHabits().getDaysOfUse();
        //Log.d("NIGHT SERV", "ID :"+id);
        Night night = database.getNightByDate(new SimpleDateFormat(Night.DATE_FORMAT).format(new Date()));
        if(night!=null){
            if(!night.getWakeUpReal().equals("") || !night.getGotToBedReal().equals("") ){
                return night;
            }
            Calendar.getInstance().add(Calendar.DAY_OF_YEAR, 1);
            String wakeUpEstimated = new SimpleDateFormat(Night.DATE_FORMAT).format(Calendar.getInstance().getTime()) + "-" + time;
            night.setWakeUpEstimated(wakeUpEstimated);
            return estimateNight(night);
        }else {
            return createNight();
        }
    }

    /*
     * Return the next night updated, if it didn't exist, it is created
     */
    public Night updateNextNight(String time){
        Calendar.getInstance().add(Calendar.DAY_OF_YEAR,1);
        String date = new SimpleDateFormat(Night.DATE_FORMAT).format(Calendar.getInstance().getTime());
        Night night = database.getNightByDate(date);
        String wakeUpEstimated = date+ "-" + time;
        if(night != null) {
            night.setWakeUpEstimated(wakeUpEstimated);
            return estimateNight(night);
        }else{
            return night;
        }
    }

    public boolean deleteNight(String date){
        return database.deleteNight(date);
    }

    public Night getNight(String date){
        return database.getNightByDate(date);
    }

    public Night getNight(Long id){return database.getNightById(id);    }
}
