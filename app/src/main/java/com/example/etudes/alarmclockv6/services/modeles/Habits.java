package com.example.etudes.alarmclockv6.services.modeles;

import com.example.etudes.alarmclockv6.services.HabitsService;

import java.util.Random;

/**
 * Created by Florian on 06/11/2017.
 */

public class Habits {
    private long id;
    private int daysOfUse;
    private int sleepHoursPerNight;
    private int daysOfLateness;
    private int rewardTime;

    public Habits(long id, int daysOfUse, int sleepHoursPerNight, int daysOfLateness, int rewardTime) {
        this.id = id;
        this.daysOfUse = daysOfUse;
        this.sleepHoursPerNight = sleepHoursPerNight;
        this.daysOfLateness = daysOfLateness;
        this.rewardTime = rewardTime;
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

    public int getDaysOfLateness() {
        return daysOfLateness;
    }

    public void setDaysOfLateness(int daysOfLateness) {
        this.daysOfLateness = daysOfLateness;
        HabitsService.getInstance().updateHabits();
    }

    public int getRewardedTime() {
        return rewardTime;
    }

    public void setRewardedTime(int rewardTime) {
        this.rewardTime = rewardTime;
        HabitsService.getInstance().updateHabits();
    }

    public int addRewardTime(int time){
        rewardTime = rewardTime + time;
        HabitsService.getInstance().updateHabits();
        return rewardTime;
    }

    public void init(){
        id = new Random().nextLong();
        daysOfUse = 0;
        sleepHoursPerNight = 7;
        rewardTime=0;
        daysOfLateness=0;
    }


}
