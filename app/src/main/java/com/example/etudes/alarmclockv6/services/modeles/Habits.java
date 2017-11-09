package com.example.etudes.alarmclockv6.services.modeles;

import com.example.etudes.alarmclockv6.services.HabitsService;

import java.util.Random;

/**
 * Created by Florian on 06/11/2017.
 */

public class Habits {
    private long id;
    private int daysOfUse;
    private int whenToAskIfWereLate;
    private int sleepHoursPerNight;

    public Habits(long id, int daysOfUse, int whenToAskIfWereLate, int sleepHoursPerNight) {
        this.id = id;
        this.daysOfUse = daysOfUse;
        this.whenToAskIfWereLate = whenToAskIfWereLate;
        this.sleepHoursPerNight = sleepHoursPerNight;
    }

    public Habits(){

    }

    public long getId() {
        return id;
    }

    public int getDaysOfUse() {
        return daysOfUse;
    }

    public void setDaysOfUse(int daysOfUse) {
        this.daysOfUse = daysOfUse;
        HabitsService.getInstance().updateHabits();
    }

    public int getWhenToAskIfWereLate() {
        return whenToAskIfWereLate;
    }

    public void setWhenToAskIfWereLate(int whenToAskIfWereLate) {
        this.whenToAskIfWereLate = whenToAskIfWereLate;
        HabitsService.getInstance().updateHabits();
    }

    public int getSleepHoursPerNight() {
        return sleepHoursPerNight;
    }

    public void setSleepHoursPerNight(int sleepHoursPerNight) {
        this.sleepHoursPerNight = sleepHoursPerNight;
        HabitsService.getInstance().updateHabits();
    }

    public int incrementDaysOfUse(){
        daysOfUse++;
        HabitsService.getInstance().updateHabits();
        return daysOfUse;
    }

    public void init(){
        id = new Random().nextLong();
        daysOfUse = 0;
        whenToAskIfWereLate = 2;
        sleepHoursPerNight = 7;
    }
}
