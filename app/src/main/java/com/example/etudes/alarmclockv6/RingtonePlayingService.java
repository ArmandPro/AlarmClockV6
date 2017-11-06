package com.example.etudes.alarmclockv6;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.etudes.alarmclockv6.R;

/**
 * Created by Etudes on 05/11/2017.
 */

public class RingtonePlayingService extends Service {


    MediaPlayer media_song;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flag, int startId){

        Log.d("in the on start command", "yey" + startId + " : "+ intent);

        media_song = MediaPlayer.create(this, R.raw.sncf);
        media_song.start();




        return START_NOT_STICKY;
    }

    public void onDestroy(){

        Toast.makeText(this, "onDestroy call", Toast.LENGTH_LONG).show();

    }
}
