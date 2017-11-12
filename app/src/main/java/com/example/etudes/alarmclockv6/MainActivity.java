package com.example.etudes.alarmclockv6;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.MiniGames.MatrixGame;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.LoginManager;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    TextView txtStatus;
    LoginButton login_button;
    CallbackManager callbackManager;
    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    Context context;
    PendingIntent pending_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseManager.getInstance(getApplicationContext());

        this.context = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initializeControls();

        logWithFacebook();

        final Intent my_intent = new Intent(this.context, AlarmReceiver.class);

        final Calendar calendar = Calendar.getInstance();

        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        Button test = (Button) findViewById(R.id.button);
        DatabaseTester tester = new DatabaseTester(getApplicationContext());
        tester.runTests();



        test.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {


                Intent gameIntent = new Intent(MainActivity.this,MatrixGame.class);
                startActivity(gameIntent);

                /*calendar.set(Calendar.HOUR_OF_DAY,alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE,alarm_timepicker.getMinute());
                alarm_timepicker = findViewById(R.id.timePicker);

                alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

                pending_intent = PendingIntent.getBroadcast(MainActivity.this,0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pending_intent);

                Log.d("kikou","lol");*/

            }







        });

        Button start = (Button) findViewById(R.id.button2);

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent nIntent = new Intent(MainActivity.this, ScrollingActivity.class);
                startActivity(nIntent);


            }
        });


    }



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
