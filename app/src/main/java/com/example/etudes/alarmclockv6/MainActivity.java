package com.example.etudes.alarmclockv6;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
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
import android.app.AlertDialog.Builder;

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


        //LOGIN WITH FACEBOOK
        this.context = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initializeControls();

        logWithFacebook();

        final Intent my_intent = new Intent(this.context, AlarmReceiver.class);

        final Calendar calendar = Calendar.getInstance();

        alarm_timepicker = (TimePicker) findViewById(R.id.timePicker);

        Button test = (Button) findViewById(R.id.button);


        //LOGIN WITH GOOGLE








        //SET ALARM
        test.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                calendar.set(Calendar.HOUR_OF_DAY,alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE,alarm_timepicker.getMinute());

                alarm_timepicker = findViewById(R.id.timePicker);

                alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

                pending_intent = PendingIntent.getBroadcast(MainActivity.this,0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pending_intent);

                Log.d("kikou","lol");

            }

        });




        //SETUP ALARM FOR WEEK
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
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set title
                alertDialogBuilder.setTitle("Night System");
                alertDialogBuilder
                        .setMessage("Are you arrive late this morning?")
                        .setCancelable(false)
                        .setPositiveButton("Yes!",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, SET LATE HERE

                            }
                        })
                        .setNegativeButton("No it's OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, SET NOT IN LATE HERE and close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
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
