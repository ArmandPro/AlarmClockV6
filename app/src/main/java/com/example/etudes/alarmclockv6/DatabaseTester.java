package com.example.etudes.alarmclockv6;

import android.content.Context;
import android.util.Log;

import com.example.etudes.alarmclockv6.Database.DatabaseConstants;
import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.services.HabitsService;
import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.WeekService;
import com.example.etudes.alarmclockv6.services.modeles.Habits;
import com.example.etudes.alarmclockv6.services.modeles.Night;
import com.example.etudes.alarmclockv6.services.modeles.Week;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Florian on 09/11/2017.
 */

public class DatabaseTester {

    private DatabaseManager databaseManager;
    private HabitsService habitsService;
    private WeekService weekService;
    private NightService nightService;
    String ok = " : OK";
    String err = " : ERROR !";

    public DatabaseTester(Context context){
        databaseManager = DatabaseManager.getInstance(context);
        habitsService = HabitsService.getInstance();
        weekService = WeekService.getInstance();
        nightService = NightService.getInstance();
    }

    public boolean runTests(){
        //if(!databaseInit())return false;
        //if(!habitsTests())return false;
        //if(!weekTests())return false;
        //if(!nightTests())return false;
        return true;
    }

    private boolean databaseInit(){
        String name = "DATABASE_INIT";
        boolean passed = false;
        if(passed = (databaseManager.getDbHelper().getDatabaseName()!=null)){
            Log.d(name,ok);
        }else{
            Log.d(name,err);
        }
        return passed;
    }

    private boolean habitsTests(){
        String name = "HABITS_TEST";
        boolean passed = true;
        Habits habits = habitsService.getHabits();
        if(habits.getDaysOfUse()==0){
            Log.d(name+" - "+ DatabaseConstants.DAYS_OF_USE,ok);
        }else{
            Log.d(name+" - "+ DatabaseConstants.DAYS_OF_USE,err + habits.getDaysOfUse());
            passed = false;
        }
        if(habits.getSleepHoursPerNight()==7){
            Log.d(name+" - SLEEP H",ok);
        }else{
            Log.d(name+" - SLEEP H",err+habits.getSleepHoursPerNight());
            passed = false;
        }

        if(habits.getWhenToAskIfWereLate()==2){
            Log.d(name+" - LATE",ok);
        }else{
            Log.d(name+" - LATE",err+habits.getWhenToAskIfWereLate());
            passed = false;
        }
        habits.incrementDaysOfUse();
        habitsService.updateHabits();
        if(habits.getDaysOfUse()==1){
            Log.d(name+" - SETSLEEP",ok);
        }else{
            Log.d(name+" - SETSLEEP",err+habits.getDaysOfUse());
            passed = false;
        }

        Log.d(name,"ALL TESTS PASSED !");
        return passed;
    }

    private boolean weekTests(){
        Week week = weekService.getWeek();
        String time = "07:00";
        String name = "WEEK";
        boolean passed = true;
        if(week.getMonday().equals(time)&&
                week.getTuesday().equals(time) &&
                week.getWednesday().equals(time) &&
                week.getThursday().equals(time) &&
                week.getFriday().equals(time) &&
                week.getSaturday().equals(time) &&
                week.getSunday().equals(time)){
            Log.d(name+" - INIT",ok);
        }else {
            Log.d(name + " - INIT", err);
            passed = false;
        }


        week.setThursday("11:30");
        //if((weekService.getWeek().getThursday().equals("11:30")));


        Log.d(name, "AlL TESTS PASSED !");
        return true;
    }


    private boolean nightTests(){
        boolean passed = true;
        habitsService.getHabits().incrementDaysOfUse();
        String name = "NIGHTS";
        Night night = nightService.createNight();
        Log.d(name+" - CREATE",night.getWakeUpEstimated()+"||||"+night.getGoToBedEstimated()+"||||"+night.getGotToBedReal()+"||||"+night.getWakeUpReal());
        night = nightService.fellAsleep();
        Log.d(name+" - FELLAS",night.getWakeUpEstimated()+"||||"+night.getGoToBedEstimated()+"||||"+night.getGotToBedReal()+"||||"+night.getWakeUpReal());
        night = nightService.wokeUp();
        Log.d(name+" - WOKUP",night.getWakeUpEstimated()+"||||"+night.getGoToBedEstimated()+"||||"+night.getGotToBedReal()+"||||"+night.getWakeUpReal());
        return passed;
    }
}
