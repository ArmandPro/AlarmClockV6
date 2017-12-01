package com.example.etudes.alarmclockv6;

import android.app.*;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.Database.SuccessPopulator;
import com.example.etudes.alarmclockv6.MiniGames.MatrixGame;
import com.example.etudes.alarmclockv6.MiniGames.RouletteGame;
import com.example.etudes.alarmclockv6.MiniGames.TapTaupeGame;
import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.modeles.Night;
import com.facebook.CallbackManager;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class DevelopperActivity extends AppCompatActivity {


    //Attributes
    TextView txtStatus;

    CallbackManager callbackManager;
    android.app.AlarmManager alarm_manager, daily_alarm_manager_dev;
    TimePicker alarm_timepicker;
    Context context;
    PendingIntent pending_intent, daily_pending_intent;

    //Animation layout
    ConstraintLayout constraintLayout;
    AnimationDrawable animationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developper);


        constraintLayout = (ConstraintLayout) findViewById(R.id.my_layout_dev);
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();


        DatabaseManager.getInstance(getApplicationContext());
        SuccessPopulator pop = new SuccessPopulator();

        //HabitsService.getInstance().getHabits().setDaysOfUse(3);






        //.....................................................AUTOMATIC DAILY CREATE NIGHT AT 18:00 PM
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,18);
        calendar.set(Calendar.MINUTE,00);

        daily_alarm_manager_dev = (android.app.AlarmManager) getSystemService(ALARM_SERVICE);

        final Intent daily_intent_dev = new Intent(getApplicationContext(), NightReceiver.class);

        daily_pending_intent = PendingIntent.getBroadcast(DevelopperActivity.this,0, daily_intent_dev, PendingIntent.FLAG_UPDATE_CURRENT);

        //each 24 hrs
        //daily_alarm_manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000, daily_pending_intent);
        //NOW
        // daily_alarm_manager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), 24*60*60*1000, daily_pending_intent);

        //TEST VERSION ! ! ! ! ! ! ! ! ! ! !
        Button testNightReceiver = (Button) findViewById(R.id.button05);

        testNightReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_YEAR,1);

                SimpleDateFormat sdf = new SimpleDateFormat(Night.DATE_FORMAT);

                NightService nightService = NightService.getInstance();
                //nightService.deleteNight(sdf.format(calendar));

                daily_alarm_manager_dev.set(android.app.AlarmManager.RTC_WAKEUP,Calendar.getInstance().getTimeInMillis(),daily_pending_intent);


            }
        });









        //........................................................................SET DUMPY ALARM
        final Intent my_intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker0);
        Button test = (Button) findViewById(R.id.button00);
        test.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                Calendar calendar2 = Calendar.getInstance();
                calendar2.set(Calendar.HOUR_OF_DAY,alarm_timepicker.getHour());
                calendar2.set(Calendar.MINUTE,alarm_timepicker.getMinute());

                alarm_timepicker = findViewById(R.id.timePicker0);

                alarm_manager = (android.app.AlarmManager) getSystemService(ALARM_SERVICE);

                pending_intent = PendingIntent.getBroadcast(DevelopperActivity.this,0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarm_manager.set(android.app.AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),pending_intent);

                Log.d("test night receiver","alamr mamanger here :" + alarm_manager.getNextAlarmClock());
                Toast.makeText(getApplicationContext(), "Alarm set up at:"+calendar2.getTime().toString(), Toast.LENGTH_SHORT).show();

            }

        });




        //...........................................................................SETUP A WEEK
        Button start = (Button) findViewById(R.id.button02);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nIntent = new Intent(DevelopperActivity.this, ScrollingActivity.class);
                startActivity(nIntent);

            }
        });


        //...............................................................SEND NOTIFICATION "LATE" AT 13:00 PM
        //EACH DAY MORNING
        //AUTOMATIC DAILY SETTING NIGHT AND ALARM AT 13:00 AM
       final Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2017,10,06,13,00);

        final android.app.AlarmManager daily_alarm_manager_morning = (android.app.AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent daily_intent_morning = new Intent(getApplicationContext(), HabitsReceiver.class);
        final PendingIntent daily_pending_intent_morning = PendingIntent.getBroadcast(DevelopperActivity.this, 0, daily_intent_morning, PendingIntent.FLAG_UPDATE_CURRENT);

        //each 24 hrs
        //daily_alarm_manager_morning.setRepeating(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(), 24*60*60*1000, daily_pending_intent_morning);

        //TEST ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !
        Button alarmBox = findViewById(R.id.button03);
        alarmBox.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                daily_alarm_manager_morning.set(android.app.AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), daily_pending_intent_morning);

            }
        });



        //................................................................NOTIFICATION DAILY SUMMARY
        final Calendar calendar4 = Calendar.getInstance();
        calendar4.set(2017,10,06,16,00);

        final android.app.AlarmManager daily_alarm_manager_summary = (android.app.AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent daily_intent_summary = new Intent(getApplicationContext(), SummaryReceiver.class);
        final PendingIntent daily_pending_intent_summary = PendingIntent.getBroadcast(DevelopperActivity.this, 0, daily_intent_summary, PendingIntent.FLAG_UPDATE_CURRENT);

        //each 24 hrs
        // daily_alarm_manager_summary.setRepeating(android.app.AlarmManager.RTC_WAKEUP, calendar4.getTimeInMillis(), 24*60*60*1000, daily_pending_intent_summary);


        //TEST Button
        Button buttonDSummary = findViewById(R.id.button9);
        buttonDSummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daily_alarm_manager_summary.set(android.app.AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(), daily_pending_intent_summary);

            }
        });




        //..................................................................TEST GO TO BED
        Button button6 = findViewById(R.id.button06);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("test","main activity button 6 clicked");
                final Intent my_intent_bed = new Intent(getApplicationContext(), GoBedReceiver.class);
                final android.app.AlarmManager alarm_manager_GTB_mon = (android.app.AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                final PendingIntent pendingIntentMonday_GTB;
                pendingIntentMonday_GTB = PendingIntent.getBroadcast(DevelopperActivity.this, 0, my_intent_bed, PendingIntent.FLAG_UPDATE_CURRENT);


                alarm_manager_GTB_mon.set(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis(),pendingIntentMonday_GTB);

            }
        });





        //................................................................................GAME
        Button testGame = (Button) findViewById(R.id.button04);
        testGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Intent> gameList = new ArrayList<>();
                gameList.add(new Intent(DevelopperActivity.this,MatrixGame.class));
                gameList.add(new Intent(DevelopperActivity.this,TapTaupeGame.class));
                gameList.add(new Intent(DevelopperActivity.this,RouletteGame.class));
                Collections.shuffle(gameList);
                startActivity(gameList.get(0));//new Random().nextBoolean()?0:1));



            }
        });


        //-------------------------------------------------------------------SUCCESS
        Button successButton = findViewById(R.id.successButton0);
        successButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent successIntent = new Intent(DevelopperActivity.this, SuccessActivity.class);
                startActivity(successIntent);
            }
        });

        //-------------------------------------------------------------------STATE
        Button stateButton = findViewById(R.id.button07);
        stateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stateIntent = new Intent(DevelopperActivity.this, StateActivity.class);
                startActivity(stateIntent);
            }
        });

    }









}
