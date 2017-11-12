package com.example.etudes.alarmclockv6;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.*;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;


import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.modeles.Night;




/**
 * Created by Etudes on 09/11/2017.
 */

public class NightReceiver extends BroadcastReceiver {

    //This method is call daily at 18:00 PM
    @Override
    public void onReceive(Context context, Intent intent) {


        final Intent my_intent = new Intent(context, AlarmReceiver.class);
        AlarmManager alarm_manager;
        PendingIntent pending_intent;
        String wakeUpEstimated;
        Date dateWakeUpEstimated  = new Date();


        Log.d("hey","here we are in the night receiver");

        //HERE CREATE A NEW NIGHT
        NightService nightService = NightService.getInstance();
        Night night = nightService.createNight();

        alarm_manager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);

        pending_intent = PendingIntent.getBroadcast(context,0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);


        wakeUpEstimated = night.getWakeUpEstimated();

        SimpleDateFormat format = new SimpleDateFormat(Night.DATE_HOUR_FORMAT);

        try {
            dateWakeUpEstimated = format.parse(wakeUpEstimated);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("hey","error when you convert string to date in he night receiver");
        }


        alarm_manager.set(AlarmManager.RTC_WAKEUP, dateWakeUpEstimated.getTime(),pending_intent);

        Log.d("hey","We have succesfully created a night");



    }
}

