package com.example.etudes.alarmclockv6;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.managers.SleepTimeOptimizer;
import com.example.etudes.alarmclockv6.managers.SuccessManager;
import com.example.etudes.alarmclockv6.services.HabitsService;
import com.example.etudes.alarmclockv6.services.NightService;

/**
 *
 * Created by: Armand on 05/11/2017.
 * This is: HabitsBroadcastService
 * Fonction: service for the habits
 *
 */
public class HabitsBroadcastService extends Service {
    MediaPlayer media_song;

    public int onStartCommand(Intent intent, int flag, int startId){


        media_song = MediaPlayer.create(this, R.raw.oldsncf);
        media_song.start();

        DatabaseManager.getInstance(getApplicationContext());
        NightService.getInstance().wasLate();
        HabitsService.getInstance().getHabits().incrementDaysOfLateness();
        SuccessManager.wasLate();
        SleepTimeOptimizer optimizer = new SleepTimeOptimizer(5,getApplicationContext());

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
