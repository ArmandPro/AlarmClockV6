package com.example.etudes.alarmclockv6;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Etudes on 12/11/2017.
 */

public class HabitsReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(final Context context, Intent intent) {

        final Intent my_intent = new Intent(context, HabitsBroadcastService.class);

        PendingIntent piOpen = PendingIntent.getService(context, 0, my_intent, 0);

        Log.d("hey", "We are in the Habits Receiver");

        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.moon)
                
                .setContentTitle("Slumber")
                .setContentText("Are you arrived late this morning?")
                .addAction(R.drawable.com_facebook_button_like_icon_selected, "Yes, I was late this morning",
                        piOpen);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());






        /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // set title
        alertDialogBuilder.setTitle("Night System");
        alertDialogBuilder
                .setMessage("Are you arrive late this morning?")
                .setCancelable(false)
                .setPositiveButton("Yes!",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, SET LATE HERE

                        Toast.makeText(context, "You're late, we are too...", Toast.LENGTH_SHORT).show();



                    }
                })
                .setNegativeButton("No it's OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, SET NOT IN LATE HERE and close
                        // the dialog box and do nothing

                        Toast.makeText(context, "Ok we'll manage that next time", Toast.LENGTH_SHORT).show();

                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();*/


        //HabitsService habitsService = HabitsService.getInstance();
       // Habits habits = habitsService.create


    }
}
