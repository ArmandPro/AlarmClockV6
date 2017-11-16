package com.example.etudes.alarmclockv6;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.etudes.alarmclockv6.services.HabitsService;
import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.modeles.Habits;
import com.example.etudes.alarmclockv6.services.modeles.Night;

/**
 * Created by Etudes on 12/11/2017.
 */

class HabitsReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(final Context context, Intent intent) {

        final Intent my_intent = new Intent(context, AlarmReceiver.class);

        Log.d("hey", "We are in the Habits Receiver");

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
        alertDialog.show();


        HabitsService habitsService = HabitsService.getInstance();
       // Habits habits = habitsService.create*/


    }
}
