package com.example.etudes.alarmclockv6;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Etudes on 13/11/2017.
 */

public class HabitsBroadcastService extends Service {
    MediaPlayer media_song;

    public int onStartCommand(Intent intent, int flag, int startId){

        Log.d("yey", "We are on the HabitsBroadcastService " + startId + " : "+ intent);

        media_song = MediaPlayer.create(this, R.raw.oldsncf);
        media_song.start();

        //INSERT HERE IN THE SQLLITE: THE USER WAS LATE

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
