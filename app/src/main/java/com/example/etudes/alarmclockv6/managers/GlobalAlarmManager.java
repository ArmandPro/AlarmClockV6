package com.example.etudes.alarmclockv6.managers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.etudes.alarmclockv6.receivers.GoBedReceiver;
import com.example.etudes.alarmclockv6.receivers.AlarmReceiver;
import com.example.etudes.alarmclockv6.services.HabitsService;
import com.example.etudes.alarmclockv6.services.WeekService;
import com.example.etudes.alarmclockv6.services.modeles.Habits;
import com.example.etudes.alarmclockv6.services.modeles.Week;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * Created by: Armand on 05/11/2017.
 * This is: GlobalAlarmManager
 * Fonction: manage all the alarm on a week
 *
 */

public class GlobalAlarmManager {


    //return 1 if we are an Monday, 2 if Tuesday...
    public int getDayOfWeek(){

        String day = new SimpleDateFormat("EE", Locale.ENGLISH).format(new Date());
        int dayOfWek = 1;
        switch (day) {
            case "Mon":
                dayOfWek = 1;
                break;
            case "Tue":
                dayOfWek = 2;
                break;
            case "Wed":
                dayOfWek = 3;
                break;
            case "Thu":
                dayOfWek = 4;
                break;
            case "Fri":
                dayOfWek = 5;
                break;
            case "Sat":
                dayOfWek = 6;
                break;
            case "Sun":
                dayOfWek = 7;
                break;
        }

        return dayOfWek;
    }



    public void updateAlarm(Context context){


        int dayOfWeek = getDayOfWeek(); //1 if monday, 2 if tuesday

        final Intent my_intent = new Intent(context, AlarmReceiver.class);

        final AlarmManager alarm_manager_mon = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        final AlarmManager alarm_manager_tue = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        final AlarmManager alarm_manager_wed = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        final AlarmManager alarm_manager_thu = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        final AlarmManager alarm_manager_fri = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        final AlarmManager alarm_manager_sat = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        final AlarmManager alarm_manager_sun = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        final PendingIntent pendingIntentMonday, pendingIntentTuesday, pendingIntentWednesday, pendingIntentThursday,pendingIntentFriday, pendingIntentSaturday,pendingIntentSunday;
        pendingIntentMonday = PendingIntent.getBroadcast(context, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntentTuesday = PendingIntent.getBroadcast(context, 1, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntentWednesday = PendingIntent.getBroadcast(context, 2, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntentThursday = PendingIntent.getBroadcast(context, 3, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntentFriday = PendingIntent.getBroadcast(context, 4, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntentSaturday = PendingIntent.getBroadcast(context, 5, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntentSunday = PendingIntent.getBroadcast(context, 6, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        WeekService weekService = WeekService.getInstance();
        Week week = weekService.getWeek();

        long oneDay = 24*60*60*1000;
        long oneWeek = 7*oneDay;
        String none = "none";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm-dd-MM-yyyy");
        String DMY = new SimpleDateFormat("-dd-MM-yyyy").format(new Date());


        //monday
        String wakeUpEstimatedMonday = week.getMonday();
        if(!wakeUpEstimatedMonday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedMonday = new Date();
            try {
                dateWakeUpEstimatedMonday = format.parse(wakeUpEstimatedMonday+DMY);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date for monday");
            }

                if(dayOfWeek>1){
                    //if we are after monday
                    alarm_manager_mon.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedMonday.getTime()+oneWeek,oneWeek,pendingIntentMonday);

                }else{
                    alarm_manager_mon.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedMonday.getTime(),oneWeek,pendingIntentMonday);
                }
        }else{
            alarm_manager_mon.cancel(pendingIntentMonday);
        }


       //tuesday
        String wakeUpEstimatedTuesday = week.getTuesday();
        if(!wakeUpEstimatedTuesday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedTuesday = new Date();
            try {
                dateWakeUpEstimatedTuesday = format.parse(wakeUpEstimatedTuesday+DMY);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date at tuesday");
            }

            if(dayOfWeek>2){
                alarm_manager_tue.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedTuesday.getTime()+1*oneDay+oneWeek,oneWeek,pendingIntentTuesday);
            }else{
                alarm_manager_tue.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedTuesday.getTime()+1*oneDay,oneWeek,pendingIntentTuesday);
            }

        }else{
            alarm_manager_tue.cancel(pendingIntentTuesday);
        }




        //wednesday
        String wakeUpEstimatedWednesday = week.getWednesday();
        if(!wakeUpEstimatedWednesday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedWednesday = new Date();
            try {
                dateWakeUpEstimatedWednesday = format.parse(wakeUpEstimatedWednesday+DMY);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date in wednesday");
            }
            if(dayOfWeek>3){
                alarm_manager_wed.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedWednesday.getTime()+2*oneDay+oneWeek,oneWeek,pendingIntentWednesday);
            }else{
                alarm_manager_wed.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedWednesday.getTime()+2*oneDay,oneWeek,pendingIntentWednesday);
            }

        }else{
            alarm_manager_wed.cancel(pendingIntentWednesday);
        }




        //Thursday
        String wakeUpEstimatedThursday = week.getThursday();
        if(!wakeUpEstimatedThursday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedThursday = new Date();

            try {
                dateWakeUpEstimatedThursday = format.parse(wakeUpEstimatedThursday+DMY);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date thursday");
            }

                if(dayOfWeek>4){
                    alarm_manager_thu.setRepeating(AlarmManager.RTC_WAKEUP, (dateWakeUpEstimatedThursday.getTime()+3*oneDay+oneWeek),oneWeek,pendingIntentThursday);

                }else{
                    alarm_manager_thu.setRepeating(AlarmManager.RTC_WAKEUP, (dateWakeUpEstimatedThursday.getTime()+3*oneDay),oneWeek,pendingIntentThursday);
                }

            }else{
            alarm_manager_thu.cancel(pendingIntentThursday);
        }



        //Friday
        String wakeUpEstimatedFriday = week.getFriday();
        if(!wakeUpEstimatedFriday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedFriday = new Date();
            try {
                dateWakeUpEstimatedFriday = format.parse(wakeUpEstimatedFriday+DMY);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date at friday");
            }
                if(dayOfWeek>5){
                    alarm_manager_fri.setRepeating(AlarmManager.RTC_WAKEUP,dateWakeUpEstimatedFriday.getTime()+4*oneDay+oneWeek ,oneWeek,pendingIntentFriday);

                }else{
                    alarm_manager_fri.setRepeating(AlarmManager.RTC_WAKEUP,dateWakeUpEstimatedFriday.getTime()+4*oneDay ,oneWeek,pendingIntentFriday);
                }

            }else{
            alarm_manager_fri.cancel(pendingIntentFriday);
        }



        //Saturday
        String wakeUpEstimatedSaturday = week.getSaturday();
        if(!wakeUpEstimatedSaturday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedSaturday = new Date();
            try {
                dateWakeUpEstimatedSaturday = format.parse(wakeUpEstimatedSaturday+DMY);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date in  Saturday");
            }

            if(dayOfWeek>6){
                alarm_manager_sat.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedSaturday.getTime()+5*oneDay+oneWeek,oneWeek,pendingIntentSaturday);

            }else{
                alarm_manager_sat.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedSaturday.getTime()+5*oneDay,oneWeek,pendingIntentSaturday);

            }

        }else{
            alarm_manager_sat.cancel(pendingIntentSaturday);
        }



        //Sunday
        String wakeUpEstimatedSunday = week.getSunday();
        if(!wakeUpEstimatedSunday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedSunday = new Date();
            try {
                dateWakeUpEstimatedSunday = format.parse(wakeUpEstimatedSunday+DMY);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date in  Saturday");
            }
                if(dayOfWeek>7){
                    //useless case
                    alarm_manager_sun.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedSunday.getTime()+6*oneDay+oneWeek,oneWeek,pendingIntentSunday);

                }else{
                    alarm_manager_sun.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedSunday.getTime()+6*oneDay,oneWeek,pendingIntentSunday);
                }

            }else{
            alarm_manager_sun.cancel(pendingIntentSunday);
        }

    }





    //This method update the go to bed time for the notification
    public void updateTimeToGoBed(Context context){

        //1 if monday, 2 if tuesday
        int dayOfWeek = getDayOfWeek();

        final Intent my_intent_bed = new Intent(context, GoBedReceiver.class);

        final AlarmManager alarm_manager_GTB_mon = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        final AlarmManager alarm_manager_GTB_tue = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        final AlarmManager alarm_manager_GTB_wed = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        final AlarmManager alarm_manager_GTB_thu = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        final AlarmManager alarm_manager_GTB_fri = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        final AlarmManager alarm_manager_GTB_sat = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        final AlarmManager alarm_manager_GTB_sun = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        final PendingIntent pendingIntentMonday_GTB, pendingIntentTuesday_GTB, pendingIntentWednesday_GTB, pendingIntentThursday_GTB,pendingIntentFriday_GTB, pendingIntentSaturday_GTB,pendingIntentSunday_GTB;
        pendingIntentMonday_GTB = PendingIntent.getBroadcast(context, 0, my_intent_bed, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntentTuesday_GTB = PendingIntent.getBroadcast(context, 1, my_intent_bed, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntentWednesday_GTB = PendingIntent.getBroadcast(context, 2, my_intent_bed, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntentThursday_GTB = PendingIntent.getBroadcast(context, 3, my_intent_bed, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntentFriday_GTB = PendingIntent.getBroadcast(context, 4, my_intent_bed, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntentSaturday_GTB = PendingIntent.getBroadcast(context, 5, my_intent_bed, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntentSunday_GTB = PendingIntent.getBroadcast(context, 6, my_intent_bed, PendingIntent.FLAG_UPDATE_CURRENT);



        WeekService weekService = WeekService.getInstance();
        Week week = weekService.getWeek();

        HabitsService habitsService = HabitsService.getInstance();
        Habits habits = habitsService.getHabits();

        long oneDay = 24*60*60*1000;
        long oneWeek = 7*oneDay;
        String none = "none";

        SimpleDateFormat format = new SimpleDateFormat("HH:mm-dd-MM-yyyy");
        String DMY = new SimpleDateFormat("-dd-MM-yyyy").format(new Date());

        int hoursOfSleep = 8;
        long millisOfSleep = 8*60*60*1000;
        hoursOfSleep = habits.getSleepHoursPerNight();
        if(hoursOfSleep>1 && hoursOfSleep<23){
            millisOfSleep = hoursOfSleep*60*60*1000;
        }else{
            millisOfSleep = 8*60*60*1000;
        }

        //monday
        String wakeUpEstimatedMonday = week.getMonday();

        if(!wakeUpEstimatedMonday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedMonday = new Date();
            try {
                dateWakeUpEstimatedMonday = format.parse(wakeUpEstimatedMonday+DMY);

            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date for monday");
            }

                if(dayOfWeek>1){
                    alarm_manager_GTB_mon.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedMonday.getTime()-millisOfSleep+oneWeek,oneWeek,pendingIntentMonday_GTB);

                }else{
                    alarm_manager_GTB_mon.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedMonday.getTime()-millisOfSleep,oneWeek,pendingIntentMonday_GTB);
                }

        }else{
            alarm_manager_GTB_mon.cancel(pendingIntentMonday_GTB);
        }



        //tuesday
        String wakeUpEstimatedTuesday = week.getTuesday();
        if(!wakeUpEstimatedTuesday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedTuesday = new Date();
            try {
                dateWakeUpEstimatedTuesday = format.parse(wakeUpEstimatedTuesday+DMY);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date at tuesday");
            }
                if(dayOfWeek>2){
                    alarm_manager_GTB_tue.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedTuesday.getTime()+1*oneDay-millisOfSleep+oneWeek,oneWeek,pendingIntentTuesday_GTB);

                }else{
                    alarm_manager_GTB_tue.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedTuesday.getTime()+1*oneDay-millisOfSleep,oneWeek,pendingIntentTuesday_GTB);
                }
        }else{
            alarm_manager_GTB_tue.cancel(pendingIntentTuesday_GTB);
        }




        //wednesday
        String wakeUpEstimatedWednesday = week.getWednesday();
        if(!wakeUpEstimatedWednesday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedWednesday = new Date();
            try {
                dateWakeUpEstimatedWednesday = format.parse(wakeUpEstimatedWednesday+DMY);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date in wednesday");
            }
                if(dayOfWeek>3){
                    alarm_manager_GTB_wed.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedWednesday.getTime()+2*oneDay-millisOfSleep+oneWeek,oneWeek,pendingIntentWednesday_GTB);

                }else{
                    alarm_manager_GTB_wed.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedWednesday.getTime()+2*oneDay-millisOfSleep,oneWeek,pendingIntentWednesday_GTB);

                }
        }else{
            alarm_manager_GTB_wed.cancel(pendingIntentWednesday_GTB);
        }




        //Thursday
        String wakeUpEstimatedThursday = week.getThursday();
        if(!wakeUpEstimatedThursday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedThursday = new Date();

            try {
                dateWakeUpEstimatedThursday = format.parse(wakeUpEstimatedThursday+DMY);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date thursday");
            }
                if(dayOfWeek>4){
                    alarm_manager_GTB_thu.setRepeating(AlarmManager.RTC_WAKEUP, (dateWakeUpEstimatedThursday.getTime()+3*oneDay-millisOfSleep+oneWeek),oneWeek,pendingIntentThursday_GTB);

                }else{
                    alarm_manager_GTB_thu.setRepeating(AlarmManager.RTC_WAKEUP, (dateWakeUpEstimatedThursday.getTime()+3*oneDay-millisOfSleep),oneWeek,pendingIntentThursday_GTB);
                }

        }else{
            alarm_manager_GTB_thu.cancel(pendingIntentThursday_GTB);
        }



        //Friday
        String wakeUpEstimatedFriday = week.getFriday();
        if(!wakeUpEstimatedFriday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedFriday = new Date();
            try {
                dateWakeUpEstimatedFriday = format.parse(wakeUpEstimatedFriday+DMY);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date at friday");
            }
                if(dayOfWeek>5){
                    alarm_manager_GTB_fri.setRepeating(AlarmManager.RTC_WAKEUP,dateWakeUpEstimatedFriday.getTime()+4*oneDay-millisOfSleep+oneWeek ,oneWeek,pendingIntentFriday_GTB);

                }else{
                    alarm_manager_GTB_fri.setRepeating(AlarmManager.RTC_WAKEUP,dateWakeUpEstimatedFriday.getTime()+4*oneDay-millisOfSleep ,oneWeek,pendingIntentFriday_GTB);

                }
        }else{
            alarm_manager_GTB_fri.cancel(pendingIntentFriday_GTB);
        }



        //Saturday
        String wakeUpEstimatedSaturday = week.getSaturday();
        if(!wakeUpEstimatedSaturday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedSaturday = new Date();
            try {
                dateWakeUpEstimatedSaturday = format.parse(wakeUpEstimatedSaturday+DMY);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date in  Saturday");
            }
                if(dayOfWeek>6){
                    alarm_manager_GTB_sat.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedSaturday.getTime()+5*oneDay-millisOfSleep+oneWeek,oneWeek,pendingIntentSaturday_GTB);

                }else{
                    alarm_manager_GTB_sat.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedSaturday.getTime()+5*oneDay-millisOfSleep,oneWeek,pendingIntentSaturday_GTB);

                }
        }else{
            alarm_manager_GTB_sat.cancel(pendingIntentSaturday_GTB);
        }

        //Sunday
        String wakeUpEstimatedSunday = week.getSunday();
        if(!wakeUpEstimatedSunday.equalsIgnoreCase(none)){
            Date dateWakeUpEstimatedSunday = new Date();
            try {
                dateWakeUpEstimatedSunday = format.parse(wakeUpEstimatedSunday+DMY);
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d("GLOBAL ALARM MANAGER","error when you convert string to date in  Saturday");
            }
                if(dayOfWeek>7){
                    alarm_manager_GTB_sun.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedSunday.getTime()+6*oneDay-millisOfSleep+oneWeek,oneWeek,pendingIntentSunday_GTB);

                }else{
                    alarm_manager_GTB_sun.setRepeating(AlarmManager.RTC_WAKEUP, dateWakeUpEstimatedSunday.getTime()+6*oneDay-millisOfSleep,oneWeek,pendingIntentSunday_GTB);
                }

           }else{
            alarm_manager_GTB_sun.cancel(pendingIntentSunday_GTB);
        }

    }
}
