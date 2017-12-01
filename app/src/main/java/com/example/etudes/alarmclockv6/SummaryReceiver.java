package com.example.etudes.alarmclockv6;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.modeles.Night;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Etudes on 22/11/2017.
 */

public class SummaryReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        DatabaseManager.getInstance(context);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();
        String lastNight = new SimpleDateFormat("yyyy/MM/dd").format(lastDate);

        Boolean sleepWell;


        NightService nightService = NightService.getInstance();
        Night night = nightService.getNight(lastNight);

        if(night == null){
            sleepWell = true;
        }else{
            sleepWell = night.isSleepWell();
        }




        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.moon)
                .setContentTitle("Slumber");

        if(sleepWell){
            notificationBuilder.setContentText("Congratulation your last night was a good night");

        }else{
            notificationBuilder.setContentText("We think your last night wasn't perfect, the next will be better!");
        }

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());






    }
}
