package com.example.etudes.alarmclockv6;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.MiniGames.MatrixGame;
import com.example.etudes.alarmclockv6.MiniGames.RouletteGame;
import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.modeles.Night;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.LoginManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.modeles.Night;


public class MainActivity extends AppCompatActivity {


    TextView txtStatus;
    LoginButton login_button;
    CallbackManager callbackManager;
    AlarmManager alarm_manager, daily_alarm_manager;
    TimePicker alarm_timepicker;
    Context context;
    PendingIntent pending_intent, daily_pending_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseManager.getInstance(getApplicationContext());



        //LOGIN WITH FACEBOOK
        this.context = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initializeControls();

        logWithFacebook();




        //DatabaseTester tester = new DatabaseTester(getApplicationContext());
        //tester.runTests();




        //LOGIN WITH GOOGLE





        //AUTOMATIC DAILY SETTING NIGHT AND ALARM AT 18:00 PM
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2017,10,06,18,00);

        daily_alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        final Intent daily_intent = new Intent(this.context, NightReceiver.class);

        daily_pending_intent = PendingIntent.getBroadcast(MainActivity.this,0, daily_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //each 24 hrs
        daily_alarm_manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000, daily_pending_intent);


        //TEST VERSION ! ! ! ! ! ! ! ! ! ! !

        Button testNightReceiver = (Button) findViewById(R.id.button5);

        testNightReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_YEAR,1);

                SimpleDateFormat sdf = new SimpleDateFormat(Night.DATE_FORMAT);

                NightService nightService = NightService.getInstance();
                //nightService.deleteNight(sdf.format(calendar));



                daily_alarm_manager.set(AlarmManager.RTC_WAKEUP,Calendar.getInstance().getTimeInMillis(),daily_pending_intent);


            }
        });









        //SET ALARM

        final Intent my_intent = new Intent(this.context, AlarmReceiver.class);

        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        Button test = (Button) findViewById(R.id.button);

        test.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                Calendar calendar2 = Calendar.getInstance();
                calendar2.set(Calendar.HOUR_OF_DAY,alarm_timepicker.getHour());
                calendar2.set(Calendar.MINUTE,alarm_timepicker.getMinute());

                alarm_timepicker = findViewById(R.id.timePicker);

                alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

                pending_intent = PendingIntent.getBroadcast(MainActivity.this,0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),pending_intent);

                Toast.makeText(getApplicationContext(), "Alarm set up at:"+calendar2.getTime().toString(), Toast.LENGTH_SHORT).show();

            }

        });




        //SETUP A WEEK
        Button start = (Button) findViewById(R.id.button2);

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent nIntent = new Intent(MainActivity.this, ScrollingActivity.class);
                startActivity(nIntent);

            }
        });


        //SHOW ALARM BOX
        Button alarmBox = findViewById(R.id.button3);

        alarmBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //daily_alarm_manager_morning.set(AlarmManager.RTC_WAKEUP,new Date().getTime(),daily_pending_intent_morning);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Night System");
                alertDialogBuilder
                        .setMessage("Are you arrive late this morning?")
                        .setCancelable(false)
                        .setPositiveButton("Yes!",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, SET LATE HERE

                                Toast.makeText(getApplicationContext(), "You're late, we are too...", Toast.LENGTH_SHORT).show();



                            }
                        })
                        .setNegativeButton("No it's OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, SET NOT IN LATE HERE and close
                                // the dialog box and do nothing

                                Toast.makeText(getApplicationContext(), "Ok we'll manage that next time", Toast.LENGTH_SHORT).show();

                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });




        //GAME

        Button testGame = (Button) findViewById(R.id.button4);
        testGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Intent> gameList = new ArrayList<>();
                gameList.add(new Intent(MainActivity.this,MatrixGame.class));
                gameList.add(new Intent(MainActivity.this,RouletteGame.class));

                startActivity(gameList.get(new Random().nextBoolean()?0:1));


            }
        });


    }



    //LOGIN WITH FACEBOOK
    private void initializeControls(){

        callbackManager = CallbackManager.Factory.create();
        txtStatus = (TextView)findViewById(R.id.txtStatus);
        login_button = (LoginButton)findViewById(R.id.login_button);

    }

    private void logWithFacebook(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                txtStatus.setText("Connection avec Facebook reussie"+loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                txtStatus.setText("Connection annulée");

            }

            @Override
            public void onError(FacebookException error) {

                txtStatus.setText("Connection avec Facebook reussie"+error.getMessage());

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



}
