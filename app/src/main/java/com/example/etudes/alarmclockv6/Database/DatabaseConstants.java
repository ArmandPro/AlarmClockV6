package com.example.etudes.alarmclockv6.Database;

/**
 * Created by Florian on 01/11/2017.
 */

public class DatabaseConstants {
    public static final String CREATE_TABLE_NIGHT = "create table NIGHT (ID integer primary key,GOTOBEDESTIMATED text not null, GOTOBEDREAL text,WAKEUPESTIMATED text not null, WAKEUPREAL text, SLEEPWELL int);";
    public static final String CREATE_TABLE_WEEK  = "create table WEEK (ID integer primary key, MONDAY text, TUESDAY text, WEDNESDAY text, THURDAY text, FRIDAY text, SATURDAY text, SUNDAY text);";
    public static final String CREATE_TABLE_HABITS = "create table HABITS (ID integer primary key,DAYSOFUSE integer not null, WHENTOASKIFWERELATE integer not null, SLEEPHOURSPERNIGHT integer not null);";
    public static final String CREATE_TABLE_SUCCESS = "create table SUCCESS (ID text primary key, NAME text, DESCRIPTION text, REWARD integer not null, ADVANCEMENT integer not null,  FINISHED integer);";
    public static final String DROP_TABLE_HABITS = "drop table HABITS;";
    public static final String DROP_TABLE_NIGHT = "drop table NIGHT;";
    public static final String DROP_TABLE_WEEK = "drop table WEEK;";
    public static final String DROP_TABLE_SUCCESS = "drop table SUCCESS;";
    public static final String TABLE_NIGHT = "NIGHT";
    public static final String TABLE_WEEK = "WEEK";
    public static final String TABLE_SUCCESS = "SUCCESS";
    public static final String ID = "ID";
    public static final String GO_TO_BED_ESTIMATED = "GOTOBEDESTIMATED";
    public static final String GO_TO_BED_REAL = "GOTOBEDREAL";
    public static final String WAKE_UP_ESTIMATED ="WAKEUPESTIMATED";
    public static final String WAKE_UP_REAL = "WAKEUPREAL";
    public static final String SLEEP_WELL ="SLEEPWELL";
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    public static final String WEDNESDAY = "WEDNESDAY";
    public static final String THURDAY ="THURDAY";
    public static final String FRIDAY = "FRIDAY";
    public static final String SATURDAY = "SATURDAY";
    public static final String SUNDAY = "SUNDAY";
    public static final String TABLE_HABITS = "HABITS";
    public static final String DAYS_OF_USE = "DAYSOFUSE";
    public static final String WHEN_TO_ASK_IF_WERE_LATE = "WHENTOASKIFWERELATE";
    public static final String  SLEEP_HOURS_PER_NIGHT = "SLEEPHOURSPERNIGHT";
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String REWARD = "REWARD";
    public static final String ADVANCEMENT = "ADVANCEMENT";
    public static final String FINISHED = "FINISHED";


}