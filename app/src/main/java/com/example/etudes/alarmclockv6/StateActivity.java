package com.example.etudes.alarmclockv6;

import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.etudes.alarmclockv6.services.modeles.Night;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StateActivity extends AppCompatActivity {

    //Animation layout
    ConstraintLayout constraintLayout;
    AnimationDrawable animationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        //annimation
        constraintLayout = (ConstraintLayout) findViewById(R.id.my_layout_state);
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();



        Date now = new Date();

        SimpleDateFormat hourFormat = new SimpleDateFormat(Night.HOUR_FORMAT);
        SimpleDateFormat dayFormat = new SimpleDateFormat(Night.DATE_FORMAT);
        SimpleDateFormat hourDayFormat = new SimpleDateFormat(Night.DATE_HOUR_FORMAT);

        Date morning = new Date();
        morning.setHours(10);
        morning.setMinutes(00);

        Date evening = new Date();
        evening.setHours(18);
        evening.setMinutes(00);








        ImageView imageView = findViewById(R.id.imageView);
        TextView textViewHi = findViewById(R.id.textViewHi);
        TextView textViewClock = findViewById(R.id.textViewClock);
        TextView textViewComment = findViewById(R.id.textViewComment);

        imageView.setImageResource(R.drawable.bluehappymoon);


        if(now.compareTo(evening)>0){
            //after evening
            textViewHi.setText("Good evening, I hope you had a good day.");



        }else if(now.compareTo(evening)<0&&now.compareTo(morning)>0){
            //betwin morning and evening
            textViewHi.setText("Enjoy your day.");



        }else if(now.compareTo(morning)<0){
            //before morning
            textViewHi.setText("Good morning, a good night annonce a good day.");


        }else{
            //other case
            textViewHi.setText("Hi you!"+now+morning+evening);



        }





    }
}
