package com.example.etudes.alarmclockv6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Etudes on 05/11/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


        Log.d("hey","we are in the receiver");

        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        context.startService(service_intent);
    }
}
