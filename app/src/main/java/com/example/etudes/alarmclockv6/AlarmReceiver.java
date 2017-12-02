package com.example.etudes.alarmclockv6;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 *
 * Created by: Armand on 05/11/2017.
 * This is: AlarmReceiver
 * Fonction: Broadcast receiver for the alarm
 *
 */

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("hey","we are in the alarm receiver");
        Intent service_intent = new Intent(context, RingtonePlayingService.class);
        context.startService(service_intent);

    }
}
