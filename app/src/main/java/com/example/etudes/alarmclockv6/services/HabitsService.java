package com.example.etudes.alarmclockv6.services;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.services.modeles.Habits;


/**
 * Created by Florian on 06/11/2017.
 */

public class HabitsService {

    private DatabaseManager database;
    private static HabitsService instance= null;
    private static Habits habits = null;

    private HabitsService(){
        database = DatabaseManager.getInstance(null);

    }

    public static HabitsService getInstance(){
        if(instance == null){
            instance = new HabitsService();
            if((habits = instance.getHabits())==null) {
                habits = new Habits();
                habits.init();
                instance.database.insertHabits(habits);
            }
        }
        return instance;
    }





    public Habits updateHabits(){
        database.updateHabits(habits);
        return habits;
    }

    public Habits getHabits(){
        if(habits!=null){
            return habits;
        }
        habits = database.getHabits();
        return habits;
    }
}
