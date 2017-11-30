package com.example.etudes.alarmclockv6;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.etudes.alarmclockv6.MiniGames.MatrixGame;
import com.example.etudes.alarmclockv6.MiniGames.RouletteGame;
import com.example.etudes.alarmclockv6.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Etudes on 05/11/2017.
 */

public class RingtonePlayingService extends Service {


    private Thread thread;
    int repeat = 10;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flag, int startId){

        Log.d("yey", "We are on the ringstone command" + startId + " : "+ intent);


        Intent myintent = new Intent(this, MatrixGame.class);

        PendingIntent pendingIntentOpenApp = PendingIntent.getActivity(this, 0, myintent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentIntent(pendingIntentOpenApp)
                .setSmallIcon(R.drawable.moon)
                .setContentTitle("Night system")
                .setContentText("Wake up!");

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());



        thread=  new Thread(){
            @Override
            public void run(){

                int i = 0;
                while(i<repeat){

                    ring();

                    try {
                        synchronized(this){
                            wait(5000);
                        }
                    }
                    catch(InterruptedException ex){
                    }
                    i++;
                }
            }
        };


        thread.start();

        
        return START_NOT_STICKY;
    }

    public void onDestroy(){

        Toast.makeText(this, "onDestroy call", Toast.LENGTH_LONG).show();

    }

    public void ring(){

        Log.d("hey", "we are in the ringer");
        MediaPlayer media_song;
        media_song = MediaPlayer.create(this, R.raw.nokia);
        media_song.start();

    }
}
