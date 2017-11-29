package com.example.etudes.alarmclockv6.managers;

import android.content.Context;

import com.example.etudes.alarmclockv6.GlobalAlarmManager;
import com.example.etudes.alarmclockv6.services.HabitsService;
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

    private WeekService weekService;//TODO  retrieve code to update alarms

    private Habits habits;
    private Context context;
    private int lateness;
    private static double[] LATENESS_LEVELS = {1,0.75,0.60,0.5,0.30,0.10,0.5};

    public  SleepTimeOptimizer(int lateness, Context context){
        weekService = WeekService.getInstance();
        habits= HabitsService.getInstance().getHabits();
        this.lateness = lateness;
        this.context = context;
        optimize();
    }

    private void optimize(){
        String todaysAlarm = weekService.getWeek().getADaysTime(new Date());
        double latenessLevel = calculateLatenessFrequency();
        int todaysImpact = getLatenessImpact(latenessLevel);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(todaysAlarm.split(":")[0]));
        calendar.set(Calendar.MINUTE,Integer.parseInt(todaysAlarm.split(":")[1])-todaysImpact);
        int weeksDay = Integer.parseInt(new SimpleDateFormat("u").format(calendar.getTime()));
        weekService.getWeek().setXdayTime(weeksDay,new SimpleDateFormat(Night.HOUR_FORMAT).format(calendar.getTime()));
        GlobalAlarmManager globalAlarmManager = new GlobalAlarmManager();
        globalAlarmManager.updateAlarm(context);
        globalAlarmManager.updateTimeToGoBed(context);
    }

    private double calculateLatenessFrequency(){
        double frequency = habits.getDaysOfLateness()/habits.getDaysOfUse();
        for(double level : LATENESS_LEVELS){
            if(frequency>=level){
                return level;
            }
        }
        return 0;
    }

    private int getLatenessImpact(double latenessLevel){
        return (int) Math.ceil(lateness*Math.pow(1+latenessLevel,2));
    }

}
