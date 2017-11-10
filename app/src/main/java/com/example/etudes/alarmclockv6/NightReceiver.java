package com.example.etudes.alarmclockv6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Etudes on 09/11/2017.
 */

public class NightReceiver extends BroadcastReceiver {

    //This method is call daily at 18:00 PM
    @Override
    public void onReceive(Context context, Intent intent) {


        Log.d("hey","here we are in the night receiver");

        //HERE CREATE A NEW NIGHT
    }
}

