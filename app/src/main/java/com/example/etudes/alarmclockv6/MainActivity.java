package com.example.etudes.alarmclockv6;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.Database.NightPopulator;
import com.example.etudes.alarmclockv6.Database.SuccessPopulator;
import com.example.etudes.alarmclockv6.managers.StateManager;
import com.example.etudes.alarmclockv6.receivers.HabitsReceiver;
import com.example.etudes.alarmclockv6.receivers.SummaryReceiver;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Calendar;


/**
 *
 * Created by: Florian and Armand on 05/11/2017.
 * This is: MainActivity
 * Fonction: the main page for the user
 *
 */

public class MainActivity extends AppCompatActivity {


    //Attributes
    TextView txtStatus;
    LoginButton login_button;
    CallbackManager callbackManager;
    AlarmManager daily_alarm_manager;
    Context context;
    PendingIntent daily_pending_intent;

    //Animation layout
    ConstraintLayout constraintLayout;
    AnimationDrawable animationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = (ConstraintLayout) findViewById(R.id.my_layout);
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();


        DatabaseManager.getInstance(getApplicationContext());
        new SuccessPopulator();
        NightPopulator.getInstance(getApplicationContext()).populate();


        //......................................................LOGIN WITH FACEBOOK
        this.context = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        initializeControls();
        logWithFacebook();


        //......................................................SET STATE INTERFACE
        ImageView imageView = findViewById(R.id.imageView);
        TextView textViewHi = findViewById(R.id.textViewHi);
        TextView textViewClock = findViewById(R.id.textViewClock);
        TextView textViewComment = findViewById(R.id.textViewComment);

        StateManager stateManager = new StateManager();
        stateManager.updateState(getApplicationContext(),imageView,textViewHi,textViewClock,textViewComment);



        //......................................................Button developper inetrface
        Button devButton = (Button) findViewById(R.id.button8);
        devButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nIntent = new Intent(MainActivity.this, DevelopperActivity.class);
                startActivity(nIntent);

            }
        });



        //.....................................................AUTOMATIC DAILY CREATE NIGHT AT 18:00 PM
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,18);
        calendar.set(Calendar.MINUTE,00);

        daily_alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent daily_intent = new Intent(this.context, NightReceiver.class);
        daily_pending_intent = PendingIntent.getBroadcast(MainActivity.this,0, daily_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //each 24 hrs
        daily_alarm_manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 24*60*60*1000, daily_pending_intent);




        //...........................................................................SETUP A WEEK
        Button start = (Button) findViewById(R.id.button2);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nIntent = new Intent(MainActivity.this, ScrollingActivity.class);
                startActivity(nIntent);

            }
        });



        //...............................................................SEND NOTIFICATION "LATE" AT 13:00 PM
        //EACH DAY MORNING
        //AUTOMATIC DAILY SETTING NIGHT AND ALARM AT 13:00 AM
        final Calendar calendar3 = Calendar.getInstance();
        calendar3.set(Calendar.HOUR_OF_DAY,13);
        calendar3.set(Calendar.MINUTE,00);

        final AlarmManager daily_alarm_manager_morning = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent daily_intent_morning = new Intent(this.context, HabitsReceiver.class);
        final PendingIntent daily_pending_intent_morning = PendingIntent.getBroadcast(MainActivity.this, 0, daily_intent_morning, PendingIntent.FLAG_UPDATE_CURRENT);

        //each 24 hrs
        daily_alarm_manager_morning.setRepeating(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(), 24*60*60*1000, daily_pending_intent_morning);





        //................................................................NOTIFICATION DAILY SUMMARY
        final Calendar calendar4 = Calendar.getInstance();
        calendar4.set(Calendar.HOUR_OF_DAY,16);
        calendar4.set(Calendar.MINUTE,00);

        final AlarmManager daily_alarm_manager_summary = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent daily_intent_summary = new Intent(this.context, SummaryReceiver.class);
        final PendingIntent daily_pending_intent_summary = PendingIntent.getBroadcast(MainActivity.this, 0, daily_intent_summary, PendingIntent.FLAG_UPDATE_CURRENT);

        //each 24 hrs
        daily_alarm_manager_summary.setRepeating(AlarmManager.RTC_WAKEUP, calendar4.getTimeInMillis(), 24*60*60*1000, daily_pending_intent_summary);







        //...................................................................SUCCESS
        Button successButton = findViewById(R.id.successButton);
        successButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent successIntent = new Intent(MainActivity.this, SuccessActivity.class);
                startActivity(successIntent);
            }
        });

        //-------------------------------------------------------------------STATUS
        Button stateButton = findViewById(R.id.button7);
        stateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stateIntent = new Intent(MainActivity.this, StateActivity.class);
                startActivity(stateIntent);
            }
        });

    }





    //.....................................................................LOGIN WITH FACEBOOK
    private void initializeControls(){
        callbackManager = CallbackManager.Factory.create();
        login_button = (LoginButton)findViewById(R.id.login_button);
    }

    private void logWithFacebook(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Log.d("facebook","Connection avec Facebook reussie"+loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("facebook","Connection annulée");

            }

            @Override
            public void onError(FacebookException error) {

                Log.d("Connect Fb echec",error.getMessage());

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
