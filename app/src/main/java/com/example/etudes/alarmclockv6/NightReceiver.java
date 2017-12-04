package com.example.etudes.alarmclockv6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;


import com.example.etudes.alarmclockv6.broadcastServices.NightBroadcastService;


/**
 *
 * Created by: Armand on 05/11/2017.
 * This is: NightReceiver
 * Fonction: receive the automation of create night
 *
 */

public class NightReceiver extends BroadcastReceiver {

    //This method is call daily at 18:00 PM
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {

        final Intent intent_alarm = new Intent(context, NightBroadcastService.class);

        context.startService(intent_alarm);

    }
}

