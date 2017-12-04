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
 *
 * Created by: Armand on 05/11/2017.
 * This is: NightBroadcastService
 * Fonction: create night each day in database
 *
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

        final AlarmManager alarm_manager_night;
        String wakeUpEstimated;
        Date dateWakeUpEstimated ;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,1);
        final PendingIntent pending_intent_alarm;

        Night night;

        SimpleDateFormat formatJour = new SimpleDateFormat(Night.DATE_FORMAT);

        NightService nightService = NightService.getInstance();

        if((night=nightService.getNight(formatJour.format(calendar.getTime())))==null){
            night = nightService.createNight();

        }

        alarm_manager_night = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);

        pending_intent_alarm = PendingIntent.getBroadcast(context,0, intent_alarm, PendingIntent.FLAG_UPDATE_CURRENT);

        alarm_manager_night.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(),pending_intent_alarm);

        return START_NOT_STICKY;
    }
}
