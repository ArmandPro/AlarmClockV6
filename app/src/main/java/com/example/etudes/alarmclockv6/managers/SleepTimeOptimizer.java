package com.example.etudes.alarmclockv6.managers;

import android.content.Context;

import com.example.etudes.alarmclockv6.GlobalAlarmManager;
import com.example.etudes.alarmclockv6.services.HabitsService;
import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.WeekService;
import com.example.etudes.alarmclockv6.services.modeles.Habits;
import com.example.etudes.alarmclockv6.services.modeles.Night;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Florian on 27/11/2017.
 */

public class SleepTimeOptimizer {

    private WeekService weekService;

    private Habits habits;
    private Context context;
    private int lateness;
    private static double[] LATENESS_LEVELS = {1,0.75,0.60,0.5,0.30,0.10,0.5};

    public  SleepTimeOptimizer(int lateness, Context context){
        weekService = WeekService.getInstance();
        habits= HabitsService.getInstance().getHabits();
        this.lateness = lateness;
        this.context = context;
        if(lateness>0)
            optimizeLateness();
        else
            optimizeGoodNights();
    }

    private void optimizeGoodNights() {
        Night night = NightService.getInstance().getLastWeekNight();
        Night today = NightService.getInstance().getNight(new SimpleDateFormat(Night.DATE_FORMAT).format(new Date()));
        if (habits.getRewardedTime() > 0 && today!=null && night!=null) {
            int earlyness = NightService.getInstance().calculateNightLateness(today);
            if (earlyness>0 && !night.isWasLate()) {
                int minutes = Math.min(earlyness,habits.getRewardedTime());
                updateTodaysAlarm(minutes);
            }
        }
    }

    private void optimizeLateness(){
        int todaysImpact = getLatenessImpact();
        updateTodaysAlarm(-1*todaysImpact);
    }

    private void updateTodaysAlarm(int valueToAdd){
        String todaysAlarm = weekService.getWeek().getADaysTime(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(todaysAlarm.split(":")[0]));
        calendar.set(Calendar.MINUTE,Integer.parseInt(todaysAlarm.split(":")[1])+valueToAdd);
        int weeksDay = Integer.parseInt(new SimpleDateFormat("u").format(calendar.getTime()));
        weekService.getWeek().setXdayTime(weeksDay,new SimpleDateFormat(Night.HOUR_FORMAT).format(calendar.getTime()));
        updateAlarms();
    }


    private double identifyLatenessFrequency(){
        double frequency = habits.getDaysOfLateness()/habits.getDaysOfUse();
        for(double level : LATENESS_LEVELS){
            if(frequency>=level){
                return level;
            }
        }
        return 0;
    }


    private int getLatenessImpact(){
        double latenessLevel = identifyLatenessFrequency();
        Night night = NightService.getInstance().getLastWeekNight();
        boolean lastWeek = false;
        if(night!=null)
            lastWeek = night.isWasLate();
        if(lastWeek)
            return (int) Math.ceil(lateness*Math.pow(1+latenessLevel,2));
        return (int) (lateness/2.0);
    }

    private void updateAlarms(){
        GlobalAlarmManager globalAlarmManager = new GlobalAlarmManager();
        globalAlarmManager.updateAlarm(context);
        globalAlarmManager.updateTimeToGoBed(context);
    }


}
