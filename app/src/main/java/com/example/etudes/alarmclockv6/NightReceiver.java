package com.example.etudes.alarmclockv6;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.*;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;


import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.modeles.Night;




/**
 * Created by Etudes on 09/11/2017.
 */

public class NightReceiver extends BroadcastReceiver {

    //This method is call daily at 18:00 PM
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceive(Context context, Intent intent) {


        Log.d("hey","here we are in the night receiver");
        final Intent intent_alarm = new Intent(context, com.example.etudes.alarmclockv6.NightBroadcastService.class);



        context.startService(intent_alarm);

    }
}

