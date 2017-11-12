package com.example.etudes.alarmclockv6.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.etudes.alarmclockv6.services.modeles.Habits;
import com.example.etudes.alarmclockv6.services.modeles.Night;
import com.example.etudes.alarmclockv6.services.modeles.Week;


/**
 * Created by Florian on 01/11/2017.
 *
 * TODO : Call the getInstance in the onCreate of the first activity, to initiate with the context
 */

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private static DatabaseManager instance = null;

    private DatabaseManager(Context context){
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public static DatabaseManager getInstance(Context context){
        if(instance == null){
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    public long insertNight(Night night){
        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.ID, night.getId());
        values.put(DatabaseConstants.GO_TO_BED_ESTIMATED,night.getGoToBedEstimated());
        values.put(DatabaseConstants.GO_TO_BED_REAL, night.getGotToBedReal());
        values.put(DatabaseConstants.WAKE_UP_ESTIMATED, night.getWakeUpEstimated());
        values.put(DatabaseConstants.WAKE_UP_REAL, night.getWakeUpReal());
        values.put(DatabaseConstants.SLEEP_WELL, night.isSleepWell());
        return database.insert(DatabaseConstants.TABLE_NIGHT, null, values);
    }

    public boolean deleteNight(String date){
        return database.delete(DatabaseConstants.TABLE_NIGHT,DatabaseConstants.WAKE_UP_ESTIMATED+" LIKE ?",new String[]{date+"%"})>0;
    }

    public boolean deleteNight(long id){
        return database.delete(DatabaseConstants.TABLE_NIGHT,DatabaseConstants.ID+" LIKE ?",new String[]{Long.toString(id)})>0;
    }

    public long updateNight(Night night){
        String date = night.getWakeUpEstimated().split("-")[0];
        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.GO_TO_BED_ESTIMATED,night.getGoToBedEstimated());
        values.put(DatabaseConstants.GO_TO_BED_REAL, night.getGotToBedReal());
        values.put(DatabaseConstants.WAKE_UP_ESTIMATED, night.getWakeUpEstimated());
        values.put(DatabaseConstants.WAKE_UP_REAL, night.getWakeUpReal());
        values.put(DatabaseConstants.SLEEP_WELL, night.isSleepWell()?0 : 1);
        return database.updateWithOnConflict(DatabaseConstants.TABLE_NIGHT,values,DatabaseConstants.WAKE_UP_ESTIMATED+" LIKE ?",new String[]{date+"%"},database.CONFLICT_REPLACE);
    }


    public Night getNightById(Long id){
        Cursor cursor = database.query(DatabaseConstants.TABLE_NIGHT,
                new String[]{"*"},
                DatabaseConstants.ID+" = ?",
                new String[]{Long.toString(id)},
                null,
                null
                ,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            String gtbe = cursor.getString(cursor.getColumnIndex(DatabaseConstants.GO_TO_BED_ESTIMATED));
            String gtbr = cursor.getString(cursor.getColumnIndex(DatabaseConstants.GO_TO_BED_REAL));
            String wue = cursor.getString(cursor.getColumnIndex(DatabaseConstants.WAKE_UP_ESTIMATED));
            String wur = cursor.getString(cursor.getColumnIndex(DatabaseConstants.WAKE_UP_REAL));
            boolean sleep = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.SLEEP_WELL))!=0;
            return new Night(id, gtbe,gtbr,wue,wur,sleep);
        }else{
            return null;
        }
    }

    public Night getNightByDate(String date){
        Cursor cursor = database.query(DatabaseConstants.TABLE_NIGHT,
                new String[]{"*"},
                DatabaseConstants.WAKE_UP_ESTIMATED+" LIKE ?",
                new String[]{date+"%"},
                null,
                null
                ,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            long id = cursor.getLong(cursor.getColumnIndex(DatabaseConstants.ID));
            String gtbe = cursor.getString(cursor.getColumnIndex(DatabaseConstants.GO_TO_BED_ESTIMATED));
            String gtbr = cursor.getString(cursor.getColumnIndex(DatabaseConstants.GO_TO_BED_REAL));
            String wue = cursor.getString(cursor.getColumnIndex(DatabaseConstants.WAKE_UP_ESTIMATED));
            String wur = cursor.getString(cursor.getColumnIndex(DatabaseConstants.WAKE_UP_REAL));
            boolean sleep = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.SLEEP_WELL))!=0;
            return new Night(id, gtbe,gtbr,wue,wur,sleep);
        }else{
            return null;
        }
    }

    public void displayNights(){
        Cursor cursor = database.query(DatabaseConstants.TABLE_NIGHT,
                new String[]{"*"},
                null,
                null,
                null,
                null
                ,null);
        if(cursor.getCount()>0){
            Night night;
            cursor.moveToFirst();
            do {
                long id = cursor.getLong(cursor.getColumnIndex(DatabaseConstants.ID));
                String gtbe = cursor.getString(cursor.getColumnIndex(DatabaseConstants.GO_TO_BED_ESTIMATED));
                String gtbr = cursor.getString(cursor.getColumnIndex(DatabaseConstants.GO_TO_BED_REAL));
                String wue = cursor.getString(cursor.getColumnIndex(DatabaseConstants.WAKE_UP_ESTIMATED));
                String wur = cursor.getString(cursor.getColumnIndex(DatabaseConstants.WAKE_UP_REAL));
                boolean sleep = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.SLEEP_WELL)) != 0;
                night = new Night(id, gtbe, gtbr, wue, wur, sleep);
                Log.d("DATABASE MANAGER",night.toString());
            }while(cursor.moveToNext());
        }else {
            Log.d("DATABASE MANAGER","No night to read");
        }
    }


    public long insertWeek(Week week){
        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.ID,week.getId());
        values.put(DatabaseConstants.MONDAY, week.getMonday());
        values.put(DatabaseConstants.TUESDAY, week.getTuesday());
        values.put(DatabaseConstants.WEDNESDAY, week.getWednesday());
        values.put(DatabaseConstants.THURDAY, week.getThursday());
        values.put(DatabaseConstants.FRIDAY, week.getFriday());
        values.put(DatabaseConstants.SATURDAY, week.getSaturday());
        values.put(DatabaseConstants.SUNDAY, week.getSunday());
        return database.insert(DatabaseConstants.TABLE_WEEK, null, values);
    }

    public long updateWeek(Week week){
        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.MONDAY, week.getMonday());
        values.put(DatabaseConstants.TUESDAY, week.getTuesday());
        values.put(DatabaseConstants.WEDNESDAY, week.getWednesday());
        values.put(DatabaseConstants.THURDAY, week.getThursday());
        values.put(DatabaseConstants.FRIDAY, week.getFriday());
        values.put(DatabaseConstants.SATURDAY, week.getSaturday());
        values.put(DatabaseConstants.SUNDAY, week.getSunday());
        return database.update(DatabaseConstants.TABLE_WEEK, values,DatabaseConstants.ID+"= ?",new String[]{Long.toString(week.getId())});
    }

    public boolean deleteWeek(long id){
        return database.delete(DatabaseConstants.TABLE_WEEK,DatabaseConstants.ID+"= ?",new String[]{Long.toString(id)})>0;
    }

    public Week getWeek(){
        Cursor cursor = database.query(DatabaseConstants.TABLE_WEEK,
                new String[]{"*"},
                null,
                null,
                null,
                null
                ,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            long id = cursor.getLong(cursor.getColumnIndex(DatabaseConstants.ID));
            String monday = cursor.getString(cursor.getColumnIndex(DatabaseConstants.MONDAY));
            String tuesday = cursor.getString(cursor.getColumnIndex(DatabaseConstants.TUESDAY));
            String wednesday = cursor.getString(cursor.getColumnIndex(DatabaseConstants.WEDNESDAY));
            String thursday = cursor.getString(cursor.getColumnIndex(DatabaseConstants.THURDAY));
            String friday = cursor.getString(cursor.getColumnIndex(DatabaseConstants.FRIDAY));
            String saturday = cursor.getString(cursor.getColumnIndex(DatabaseConstants.SATURDAY));
            String sunday = cursor.getString(cursor.getColumnIndex(DatabaseConstants.SUNDAY));
            return new Week(id, monday,tuesday,wednesday,thursday,friday,saturday,sunday);
        }
        return null;
    }


    public long insertHabits(Habits habits){
        Log.d("DATABASE MANAGER","INSERT HABITS");
        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.ID, habits.getId());
        values.put(DatabaseConstants.DAYS_OF_USE,habits.getDaysOfUse());
        values.put(DatabaseConstants.WHEN_TO_ASK_IF_WERE_LATE, habits.getWhenToAskIfWereLate());
        values.put(DatabaseConstants.SLEEP_HOURS_PER_NIGHT, habits.getSleepHoursPerNight());
        return database.insert(DatabaseConstants.TABLE_HABITS,null,values);
    }

    public long updateHabits(Habits habits){
        ContentValues values = new ContentValues();
        values.put(DatabaseConstants.DAYS_OF_USE, habits.getDaysOfUse());
        values.put(DatabaseConstants.WHEN_TO_ASK_IF_WERE_LATE, habits.getWhenToAskIfWereLate());
        values.put(DatabaseConstants.SLEEP_HOURS_PER_NIGHT, habits.getSleepHoursPerNight());
        return database.update(DatabaseConstants.TABLE_HABITS,values,DatabaseConstants.ID+" = ?", new String[]{Long.toString(habits.getId())});
    }

    public Habits getHabits(){
        Cursor cursor = database.query(DatabaseConstants.TABLE_HABITS,
                new String[]{"*"},
                null,
                null,
                null,
                null
                ,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            Habits habits = new Habits();
            Long id = cursor.getLong(cursor.getColumnIndex(DatabaseConstants.ID));
            int daysOfUse = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.DAYS_OF_USE));
            int sleepHoursPerNight = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.SLEEP_HOURS_PER_NIGHT));
            int whenToAskIfWereLate = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.WHEN_TO_ASK_IF_WERE_LATE));
            return new Habits(id, daysOfUse,whenToAskIfWereLate,sleepHoursPerNight);
        }
        return null;
    }

    public DatabaseHelper getDbHelper() {
        return dbHelper;
    }
}
