package com.example.etudes.alarmclockv6.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.etudes.alarmclockv6.R;
import com.example.etudes.alarmclockv6.services.modeles.Habits;
import com.example.etudes.alarmclockv6.services.modeles.Night;
import com.example.etudes.alarmclockv6.services.modeles.Success;
import com.example.etudes.alarmclockv6.services.modeles.Week;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Florian on 01/11/2017.
 *
 * Manages data with CRUD functions
 */

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private static DatabaseManager instance = null;
    private Context context;

    private DatabaseManager(Context context){
        dbHelper = new DatabaseHelper(context);
        this.context = context;
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
        values.put(context.getString(R.string.ID), night.getId());
        values.put(context.getString(R.string.GO_TO_BED_ESTIMATED),night.getGoToBedEstimated());
        values.put(context.getString(R.string.GO_TO_BED_REAL), night.getGotToBedReal());
        values.put(context.getString(R.string.WAKE_UP_ESTIMATED), night.getWakeUpEstimated());
        values.put(context.getString(R.string.WAKE_UP_REAL), night.getWakeUpReal());
        values.put(context.getString(R.string.SLEEP_WELL), night.isSleepWell());
        return database.insert(context.getString(R.string.TABLE_NIGHT), null, values);
    }


    public long updateNight(Night night){
        String date = night.getWakeUpEstimated().split("-")[0];
        ContentValues values = new ContentValues();
        values.put(context.getString(R.string.GO_TO_BED_ESTIMATED),night.getGoToBedEstimated());
        values.put(context.getString(R.string.GO_TO_BED_REAL), night.getGotToBedReal());
        values.put(context.getString(R.string.WAKE_UP_ESTIMATED), night.getWakeUpEstimated());
        values.put(context.getString(R.string.WAKE_UP_REAL), night.getWakeUpReal());
        values.put(context.getString(R.string.SLEEP_WELL), night.isSleepWell()?0 : 1);
        return database.updateWithOnConflict(context.getString(R.string.TABLE_NIGHT),values,context.getString(R.string.WAKE_UP_ESTIMATED)+" LIKE ?",new String[]{date+"%"},database.CONFLICT_REPLACE);
    }

    public long getLastNightId(){
        Cursor cursor = database.query(context.getString(R.string.TABLE_NIGHT),
                new String[]{"ID"},
                null,
                null,
                null,
                null,
                context.getString(R.string.ID)+" DESC",
                "1");
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            return cursor.getLong(cursor.getColumnIndex(context.getString(R.string.ID)));
        }
        return -1;
    }

    public Night getNightByDate(String date){
        Cursor cursor = database.query(context.getString(R.string.TABLE_NIGHT),
                new String[]{"*"},
                context.getString(R.string.WAKE_UP_ESTIMATED)+" LIKE ?",
                new String[]{date+"%"},
                null,
                null
                ,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            long id = cursor.getLong(cursor.getColumnIndex(context.getString(R.string.ID)));
            String gtbe = cursor.getString(cursor.getColumnIndex(context.getString(R.string.GO_TO_BED_ESTIMATED)));
            String gtbr = cursor.getString(cursor.getColumnIndex(context.getString(R.string.GO_TO_BED_REAL)));
            String wue = cursor.getString(cursor.getColumnIndex(context.getString(R.string.WAKE_UP_ESTIMATED)));
            String wur = cursor.getString(cursor.getColumnIndex(context.getString(R.string.WAKE_UP_REAL)));
            boolean sleep = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.SLEEP_WELL)))!=0;
            boolean late = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.WAS_LATE)))!=0;
            return new Night(id, gtbe,gtbr,wue,wur,sleep, late);
        }else{
            return null;
        }
    }

    public List<Night> getLastNights(){
        List<Night> nights = new ArrayList<>();
        Cursor cursor = database.query(context.getString(R.string.TABLE_NIGHT),
                new String[]{"*"},
                null,
                null,
                null,
                null,
                context.getString(R.string.ID)+" DESC",
                "7");
        if(cursor.getCount()>0){

            Night night;
            cursor.moveToFirst();
            do {
                long id = cursor.getLong(cursor.getColumnIndex(context.getString(R.string.ID)));
                String gtbe = cursor.getString(cursor.getColumnIndex(context.getString(R.string.GO_TO_BED_ESTIMATED)));
                String gtbr = cursor.getString(cursor.getColumnIndex(context.getString(R.string.GO_TO_BED_REAL)));
                String wue = cursor.getString(cursor.getColumnIndex(context.getString(R.string.WAKE_UP_ESTIMATED)));
                String wur = cursor.getString(cursor.getColumnIndex(context.getString(R.string.WAKE_UP_REAL)));
                boolean sleep = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.SLEEP_WELL))) != 0;
                boolean late = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.WAS_LATE)))!=0;
                night = new Night(id, gtbe, gtbr, wue, wur, sleep,late);
                nights.add(night);
            }while(cursor.moveToNext());
        }
        return nights;
    }

    public void displayNights(){
        Cursor cursor = database.query(context.getString(R.string.TABLE_NIGHT),
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
                long id = cursor.getLong(cursor.getColumnIndex(context.getString(R.string.ID)));
                String gtbe = cursor.getString(cursor.getColumnIndex(context.getString(R.string.GO_TO_BED_ESTIMATED)));
                String gtbr = cursor.getString(cursor.getColumnIndex(context.getString(R.string.GO_TO_BED_REAL)));
                String wue = cursor.getString(cursor.getColumnIndex(context.getString(R.string.WAKE_UP_ESTIMATED)));
                String wur = cursor.getString(cursor.getColumnIndex(context.getString(R.string.WAKE_UP_REAL)));
                boolean sleep = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.SLEEP_WELL))) != 0;
                boolean late = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.WAS_LATE)))!=0;
                night = new Night(id, gtbe, gtbr, wue, wur, sleep,late);
                Log.d("DATABASE MANAGER",night.toString());
            }while(cursor.moveToNext());
        }else {
            Log.d("DATABASE MANAGER","No night to read");
        }
    }


    public long insertWeek(Week week){
        ContentValues values = new ContentValues();
        values.put(context.getString(R.string.ID),week.getId());
        values.put(context.getString(R.string.MONDAY), week.getMonday());
        values.put(context.getString(R.string.TUESDAY), week.getTuesday());
        values.put(context.getString(R.string.WEDNESDAY), week.getWednesday());
        values.put(context.getString(R.string.THURDAY), week.getThursday());
        values.put(context.getString(R.string.FRIDAY), week.getFriday());
        values.put(context.getString(R.string.SATURDAY), week.getSaturday());
        values.put(context.getString(R.string.SUNDAY), week.getSunday());
        return database.insert(context.getString(R.string.TABLE_WEEK), null, values);
    }

    public long updateWeek(Week week){
        ContentValues values = new ContentValues();
        values.put(context.getString(R.string.MONDAY), week.getMonday());
        values.put(context.getString(R.string.TUESDAY), week.getTuesday());
        values.put(context.getString(R.string.WEDNESDAY), week.getWednesday());
        values.put(context.getString(R.string.THURDAY), week.getThursday());
        values.put(context.getString(R.string.FRIDAY), week.getFriday());
        values.put(context.getString(R.string.SATURDAY), week.getSaturday());
        values.put(context.getString(R.string.SUNDAY), week.getSunday());
        return database.update(context.getString(R.string.TABLE_WEEK), values,context.getString(R.string.ID)+"= ?",new String[]{Long.toString(week.getId())});
    }

    public boolean deleteWeek(long id){
        return database.delete(context.getString(R.string.TABLE_WEEK),context.getString(R.string.ID)+"= ?",new String[]{Long.toString(id)})>0;
    }

    public Week getWeek(){
        Cursor cursor = database.query(context.getString(R.string.TABLE_WEEK),
                new String[]{"*"},
                null,
                null,
                null,
                null
                ,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            long id = cursor.getLong(cursor.getColumnIndex(context.getString(R.string.ID)));
            String monday = cursor.getString(cursor.getColumnIndex(context.getString(R.string.MONDAY)));
            String tuesday = cursor.getString(cursor.getColumnIndex(context.getString(R.string.TUESDAY)));
            String wednesday = cursor.getString(cursor.getColumnIndex(context.getString(R.string.WEDNESDAY)));
            String thursday = cursor.getString(cursor.getColumnIndex(context.getString(R.string.THURDAY)));
            String friday = cursor.getString(cursor.getColumnIndex(context.getString(R.string.FRIDAY)));
            String saturday = cursor.getString(cursor.getColumnIndex(context.getString(R.string.SATURDAY)));
            String sunday = cursor.getString(cursor.getColumnIndex(context.getString(R.string.SUNDAY)));
            return new Week(id, monday,tuesday,wednesday,thursday,friday,saturday,sunday);
        }
        return null;
    }


    public long insertHabits(Habits habits){
        ContentValues values = new ContentValues();
        values.put(context.getString(R.string.ID), habits.getId());
        values.put(context.getString(R.string.DAYS_OF_USE),habits.getDaysOfUse());
        values.put(context.getString(R.string.DAYS_OF_LATENESS),habits.getDaysOfLateness());
        values.put(context.getString(R.string.REWARD_TIME),habits.getRewardedTime());
        values.put(context.getString(R.string.SLEEP_HOURS_PER_NIGHT), habits.getSleepHoursPerNight());
        return database.insert(context.getString(R.string.TABLE_HABITS),null,values);
    }

    public long updateHabits(Habits habits){
        ContentValues values = new ContentValues();
        values.put(context.getString(R.string.DAYS_OF_USE), habits.getDaysOfUse());
        values.put(context.getString(R.string.DAYS_OF_LATENESS),habits.getDaysOfLateness());
        values.put(context.getString(R.string.REWARD_TIME),habits.getRewardedTime());
        values.put(context.getString(R.string.SLEEP_HOURS_PER_NIGHT), habits.getSleepHoursPerNight());
        return database.update(context.getString(R.string.TABLE_HABITS),values,context.getString(R.string.ID)+" = ?", new String[]{Long.toString(habits.getId())});
    }

    public Habits getHabits(){
        Cursor cursor = database.query(context.getString(R.string.TABLE_HABITS),
                new String[]{"*"},
                null,
                null,
                null,
                null
                ,null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            Habits habits = new Habits();
            Long id = cursor.getLong(cursor.getColumnIndex(context.getString(R.string.ID)));
            int daysOfUse = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.DAYS_OF_USE)));
            int sleepHoursPerNight = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.SLEEP_HOURS_PER_NIGHT)));
            int rewardTime = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.REWARD_TIME)));
            int daysOfLateness = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.DAYS_OF_LATENESS)));
            return new Habits(id, daysOfUse,sleepHoursPerNight, daysOfLateness,rewardTime);
        }
        Habits habits = new Habits();
        habits.init();
        return habits;
    }

    public List<Success> getSuccesses(){
        List<Success> results = new ArrayList<>();
        Cursor cursor = database.query(context.getString(R.string.TABLE_SUCCESS),
                new String[]{"*"},
                null,
                null,
                null,
                null
                ,null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                Habits habits = new Habits();
                String id = cursor.getString(cursor.getColumnIndex(context.getString(R.string.ID)));
                String name = cursor.getString(cursor.getColumnIndex(context.getString(R.string.NAME)));
                String desc = cursor.getString(cursor.getColumnIndex(context.getString(R.string.DESCRIPTION)));
                int reward = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.REWARD)));
                int advancement = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.ADVANCEMENT)));
                boolean finished = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.FINISHED))) == 1;
                results.add(new Success(id, name, desc, reward, advancement, finished));
            } while (cursor.moveToNext());
            return results;
        }
        return null;
    }

    public List<Success> getSuccessesByKey(String key){
        List<Success> results = new ArrayList<>();
        Cursor cursor = database.query(context.getString(R.string.TABLE_SUCCESS),
                new String[]{"*"},
                context.getString(R.string.ID)+" like ? and "+ context.getString(R.string.FINISHED)+" = 0",
                new String[]{key},
                null,
                null
                ,null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                Habits habits = new Habits();
                String id = cursor.getString(cursor.getColumnIndex(context.getString(R.string.ID)));
                String name = cursor.getString(cursor.getColumnIndex(context.getString(R.string.NAME)));
                String desc = cursor.getString(cursor.getColumnIndex(context.getString(R.string.DESCRIPTION)));
                int reward = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.REWARD)));
                int advancement= cursor.getInt(cursor.getColumnIndex(context.getString(R.string.ADVANCEMENT)));
                boolean finished = cursor.getInt(cursor.getColumnIndex(context.getString(R.string.FINISHED))) == 1;
                results.add(new Success(id, name, desc, reward, advancement, finished));
            } while (cursor.moveToNext());
            return results;
        }
        return null;
    }

    public long insertSuccess(Success success){
        ContentValues values = new ContentValues();
        values.put(context.getString(R.string.ID), success.getId());
        values.put(context.getString(R.string.NAME), success.getName());
        values.put(context.getString(R.string.DESCRIPTION), success.getDescription());
        values.put(context.getString(R.string.REWARD), success.getReward());
        values.put(context.getString(R.string.ADVANCEMENT), success.getAdvancement());
        values.put(context.getString(R.string.FINISHED), success.isFinished()?1:0);
        return database.insert(context.getString(R.string.TABLE_SUCCESS),null,values);
    }

    public long updateSucces(Success success){
        ContentValues values = new ContentValues();
        values.put(context.getString(R.string.NAME), success.getName());
        values.put(context.getString(R.string.DESCRIPTION), success.getDescription());
        values.put(context.getString(R.string.REWARD), success.getReward());
        values.put(context.getString(R.string.ADVANCEMENT), success.getAdvancement());
        values.put(context.getString(R.string.FINISHED), success.isFinished()?1:0);
        return database.update(context.getString(R.string.TABLE_SUCCESS),values, context.getString(R.string.ID)+" = ? ", new String[]{success.getId()});
    }



    public DatabaseHelper getDbHelper() {
        return dbHelper;
    }
}
