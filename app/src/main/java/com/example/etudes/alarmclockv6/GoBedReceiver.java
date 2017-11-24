package com.example.etudes.alarmclockv6;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.modeles.Night;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Etudes on 22/11/2017.
 */

public class GoBedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("test","we are in the GoBedReceiver");

        int numberOfChecking = 10;
        long intervalOfChecking = 5*60*1000;
        //FOR TEST : long intervalOfChecking = 1000;
        boolean probablySleeping[] = {false,false,false,false,false,false,false,false,false,false,false};
        boolean fallAsleepDetected = false;


        String today = new SimpleDateFormat(Night.DATE_FORMAT).format(new Date());
        NightService nightService = NightService.getInstance();
        Night night = nightService.getNight(today);


        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.moon)
                .setContentTitle("Night system")
                .setContentText("You should go to bed :)");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());

        int i = 0;
        while(i < numberOfChecking && !fallAsleepDetected){

            try {
                IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                Intent batteryStatus = context.registerReceiver(null, ifilter);
                int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                probablySleeping[i]=status == BatteryManager.BATTERY_STATUS_CHARGING;
                if(i>3){
                    if(probablySleeping[i]&&probablySleeping[i-1]&&probablySleeping[i-2]&&probablySleeping[i-3]){

                        //user is probably sleeping
                        night.setGotToBedReal(new SimpleDateFormat(Night.DATE_HOUR_FORMAT).format(new Date(new Date().getTime()-4*intervalOfChecking)));

                        fallAsleepDetected=true;
                        Log.d("GoBedReceiver", "he fall asleep");
                    }
                }

                Thread.sleep(intervalOfChecking);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            i++;
        }

        if(!fallAsleepDetected){
            //user is probably sleeping
            night.setGotToBedReal(new SimpleDateFormat(Night.DATE_HOUR_FORMAT).format(new Date()));

            Log.d("GoBedReceiver", "smartphone not pluged");
        }


    }
}
