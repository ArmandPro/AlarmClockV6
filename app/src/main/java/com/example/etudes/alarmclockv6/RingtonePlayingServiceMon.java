package com.example.etudes.alarmclockv6;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Etudes on 16/11/2017.
 */

public class RingtonePlayingServiceMon extends Service {


    MediaPlayer media_song;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flag, int startId){

        Log.d("yey", "We are on the ringstone monday" + startId + " : "+ intent);

        media_song = MediaPlayer.create(this, R.raw.iphone);
        media_song.start();

        return START_NOT_STICKY;
    }

    public void onDestroy(){

        Toast.makeText(this, "onDestroy call", Toast.LENGTH_LONG).show();

    }
}
