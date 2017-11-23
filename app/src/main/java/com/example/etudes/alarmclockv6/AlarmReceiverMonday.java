package com.example.etudes.alarmclockv6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Etudes on 16/11/2017.
 */

public class AlarmReceiverMonday extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("hey","we are in the alarm receiver monday");

        Intent service_intent = new Intent(context, RingtonePlayingServiceMon.class);

        context.startService(service_intent);

        //final Intent my_intent = new Intent(context, AlarmReceiver.class);



    }
}