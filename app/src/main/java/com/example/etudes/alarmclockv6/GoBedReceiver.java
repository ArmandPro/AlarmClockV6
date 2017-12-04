package com.example.etudes.alarmclockv6;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.BatteryManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.services.NightService;

/**
 *
 * Created by: Armand on 05/11/2017.
 * This is: GoBedReceiver
 * Fonction: receive go to bed notification
 *
 */

public class GoBedReceiver extends BroadcastReceiver {

    private Thread thread;
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener gyroscopeEventListener;

    int numberOfChecking = 10;
    long intervalOfChecking = 5*60*1000;
    //long intervalOfChecking = 5000; //FOR TEST
    boolean probablySleeping[] = {false,false,false,false,false,false,false,false,false,false,false};
    boolean gyroMoved = false;
    boolean fallAsleepDetected = false;




    @Override
    public void onReceive(Context context, Intent intent) {

        DatabaseManager.getInstance(context);

        NightService nightService = NightService.getInstance();
        Night night = nightService.getNight(new SimpleDateFormat(Night.DATE_FORMAT).format(new Date()));



        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.moon)
                .setContentTitle("Slumber")
                .setContentText("You should go to bed :)");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());

        testIfSleep(context);


    }



    //this method launch a thread and check if the user sleep
    void testIfSleep(final Context context){
        thread=  new Thread(){
            @Override
            public void run(){

                //gyroscope
                sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
                gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

                gyroscopeEventListener = new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent sensorEvent) {
                        if(sensorEvent.values[2] > 0.5f || sensorEvent.values[2] < (-0.5f)){
                            gyroMoved=true;
                        }
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int i) {

                    }
                };

                if(gyroscopeSensor==null)
                    Log.d("gyroscope", "not available in the device");


                int i = 0;
                while(i < numberOfChecking && !fallAsleepDetected){

                        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                        Intent batteryStatus = context.registerReceiver(null, ifilter);
                        //if charging
                        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

                        probablySleeping[i]=(status == BatteryManager.BATTERY_STATUS_CHARGING);
                        if(i>3){
                            if(probablySleeping[i]&&probablySleeping[i-1]&&probablySleeping[i-2]&&probablySleeping[i-3]&&(!gyroMoved)){

                                //user is probably sleeping
                                NightService.getInstance().fellAsleep();

                                fallAsleepDetected=true;
                            }
                        }

                        try {
                            synchronized(this){
                                wait(intervalOfChecking);
                            }
                        }
                        catch(InterruptedException ex){
                        }

                    gyroMoved = false;
                    i++;
                }

                if(!fallAsleepDetected){
                    //user is probably sleeping
                    NightService.getInstance().fellAsleep();
                }
            }
        };

        thread.start();
    }
}
