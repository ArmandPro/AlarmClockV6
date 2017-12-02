package com.example.etudes.alarmclockv6;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 *
 * Created by: Armand on 05/11/2017.
 * This is: HabitsReceiver
 * Fonction: Notify the user and ask when he arrive
 *
 */
public class HabitsReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(final Context context, Intent intent) {

        final Intent my_intent = new Intent(context, HabitsBroadcastService.class);

        PendingIntent piOpen = PendingIntent.getService(context, 0, my_intent, 0);

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.moon)
                
                .setContentTitle("Slumber")
                .setContentText("Are you arrived late this morning?")
                .addAction(R.drawable.com_facebook_button_like_icon_selected, "Yes, I was late this morning",
                        piOpen);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());



    }
}
