package com.example.etudes.alarmclockv6;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.modeles.Night;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Etudes on 13/11/2017.
 */

public class NightBroadcastService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flag, int startId){

        Context context = getApplicationContext();

        final Intent intent_alarm = new Intent(context, com.example.etudes.alarmclockv6.RingtonePlayingService.class);


        Log.d("hey", "we are in the NightBroadcastService");

        final AlarmManager alarm_manager_night;
        String wakeUpEstimated;
        Date dateWakeUpEstimated ;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,1);
        final PendingIntent pending_intent_alarm;






        Night night;
        //HERE CREATE A NEW NIGHT
        SimpleDateFormat formatJour = new SimpleDateFormat(Night.DATE_FORMAT);
        NightService nightService = NightService.getInstance();
        if((night=nightService.getNight(formatJour.format(calendar.getTime())))==null){
            night = nightService.createNight();
            Log.d("hey","night created");

        }




        alarm_manager_night = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);


        //pending_intent_alarm = PendingIntent.getBroadcast(context,0, intent_alarm, PendingIntent.FLAG_UPDATE_CURRENT);

        pending_intent_alarm = PendingIntent.getBroadcast(context,0, intent_alarm, PendingIntent.FLAG_UPDATE_CURRENT);



        wakeUpEstimated = night.getWakeUpEstimated();
        Log.d("hey","wakeUpestimated:"+wakeUpEstimated.toString());

        SimpleDateFormat format = new SimpleDateFormat(Night.DATE_HOUR_FORMAT);

        try {
            dateWakeUpEstimated = format.parse(wakeUpEstimated);
            Log.d("hey","dateWakeUpEstimated"+dateWakeUpEstimated.toString());
            Log.d("hey","dateWakeUpEstimated in millis:"+dateWakeUpEstimated.getTime());
            Calendar calendarTest = Calendar.getInstance();
            calendarTest.set(2017,10,18,10,55);
            Log.d("hey","calendar in millis:"+calendarTest.getTimeInMillis());

        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("hey","error when you convert string to date in he night receiver");
        }




        //alarm_manager_night.set(AlarmManager.RTC_WAKEUP, dateWakeUpEstimated.getTime(),pending_intent_alarm);

        alarm_manager_night.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(),pending_intent_alarm);


        //Log.d("hey","We have setup the alarm at:"+dateWakeUpEstimated.toString());




        return START_NOT_STICKY;
    }
}
