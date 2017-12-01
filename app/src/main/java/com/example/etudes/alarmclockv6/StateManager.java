package com.example.etudes.alarmclockv6;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.WeekService;
import com.example.etudes.alarmclockv6.services.modeles.Night;
import com.example.etudes.alarmclockv6.services.modeles.Week;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Etudes on 30/11/2017.
 */

public class StateManager {



    public void updateState(Context context, ImageView imageView, TextView textViewHi, TextView textViewClock, TextView textViewComment){

        DatabaseManager.getInstance(context);

        NightService nightService = NightService.getInstance();
        Night night = nightService.getNight(new SimpleDateFormat(Night.DATE_FORMAT).format(new Date()));
        WeekService weekService = WeekService.getInstance();
        Week week = weekService.getWeek();






        //..............................textViewHi

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


        //..........................................textViewClock

        String wakeUpEstimated = "none";
        int currentDay = getDayOfWeek()+1;  // = tomorrow
        if(currentDay == 1){
            wakeUpEstimated = week.getMonday();
        }else if(currentDay == 2){
            wakeUpEstimated = week.getTuesday();
        }else if(currentDay == 3){
            wakeUpEstimated = week.getWednesday();
        }else if(currentDay == 4){
            wakeUpEstimated = week.getThursday();
        }else if(currentDay == 5){
            wakeUpEstimated = week.getFriday();
        }else if(currentDay == 6){
            wakeUpEstimated = week.getSaturday();
        }else if(currentDay == 7){
            wakeUpEstimated = week.getSunday();
        }

        if(wakeUpEstimated.equalsIgnoreCase("none")){
            textViewClock.setText("No alarm programmed for tomorrow");
            imageView.setImageResource(R.drawable.ideamoon);
        }else if(wakeUpEstimated != null){
            textViewClock.setText("Alarm tomorrow at: "+wakeUpEstimated);
        }



        //..........................................textViewComment
        DatabaseManager.getInstance(context);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();
        String lastNight = new SimpleDateFormat("yyyy/MM/dd").format(lastDate);

        Boolean sleepWell;

        if(night == null){
            sleepWell = true;
        }else{
            sleepWell = night.isSleepWell();
        }

        if(sleepWell){
            textViewComment.setText("Your last night was good");
        }else{
            textViewComment.setText("We think your last night wasn't perfect :(");
            imageView.setImageResource(R.drawable.sadmoon);

        }


    }


    //return 1 if we are an Monday, 2 if Tuesday...
    public int getDayOfWeek(){

        String day = new SimpleDateFormat("EE", Locale.ENGLISH).format(new Date());
        int dayOfWek = 1;
        switch (day) {
            case "Mon":
                dayOfWek = 1;
                break;
            case "Tue":
                dayOfWek = 2;
                break;
            case "Wed":
                dayOfWek = 3;
                break;
            case "Thu":
                dayOfWek = 4;
                break;
            case "Fri":
                dayOfWek = 5;
                break;
            case "Sat":
                dayOfWek = 6;
                break;
            case "Sun":
                dayOfWek = 7;
                break;
        }

        Log.d("DayOfWeek", "number"+dayOfWek);
        return dayOfWek;
    }

}
