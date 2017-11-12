package com.example.etudes.alarmclockv6;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;

import com.example.etudes.alarmclockv6.services.WeekService;
import com.example.etudes.alarmclockv6.services.modeles.Week;


public class ScrollingActivity extends AppCompatActivity {

    private TimePicker TP0, TP1, TP2, TP3, TP4, TP5, TP6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        TP0 = (TimePicker) findViewById(R.id.timePicker0);
        TP1 = (TimePicker) findViewById(R.id.timePicker1);
        TP2 = (TimePicker) findViewById(R.id.timePicker2);
        TP3 = (TimePicker) findViewById(R.id.timePicker3);
        TP4 = (TimePicker) findViewById(R.id.timePicker4);
        TP5 = (TimePicker) findViewById(R.id.timePicker5);
        TP6 = (TimePicker) findViewById(R.id.timePicker6);

        loadWeek();

        final Switch s0 = (Switch) findViewById(R.id.switch0);
        final Switch s1 = (Switch) findViewById(R.id.switch1);
        final Switch s2 = (Switch) findViewById(R.id.switch2);
        final Switch s3 = (Switch) findViewById(R.id.switch3);
        final Switch s4 = (Switch) findViewById(R.id.switch4);
        final Switch s5 = (Switch) findViewById(R.id.switch5);
        final Switch s6 = (Switch) findViewById(R.id.switch6);


        Button buttonSetWeek = (Button) findViewById(R.id.buttonSetWeek);

        buttonSetWeek.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                WeekService weekService = WeekService.getInstance();
                Week week = weekService.getWeek();
                if (s0.isChecked()) week.setMonday(TP0.getHour() + ":" + TP0.getMinute());
                if (s0.isChecked()) week.setTuesday(TP1.getHour() + ":" + TP1.getMinute());
                if (s0.isChecked()) week.setWednesday(TP2.getHour() + ":" + TP2.getMinute());
                if (s0.isChecked()) week.setThursday(TP3.getHour() + ":" + TP3.getMinute());
                if (s0.isChecked()) week.setFriday(TP4.getHour() + ":" + TP4.getMinute());
                if (s0.isChecked()) week.setSaturday(TP5.getHour() + ":" + TP5.getMinute());
                if (s0.isChecked()) week.setSunday(TP6.getHour() + ":" + TP6.getMinute());

                //WeekService weekService = new WeekService(getApplicationContext());

                //weekService.createWeek(day0,day1,day2,day3,day4,day5,day6);


                //Toast toast1=Toast.makeText(getApplicationContext(),day2,Toast.LENGTH_LONG);
                //toast1.setMargin(50,50);
                //toast1.show();

            }
        });
    }

    private void loadWeek() {
        Week week = WeekService.getInstance().getWeek();
        String none = "none";
        if (!week.getMonday().equals(none)) {
            TP0.setHour(Integer.parseInt(week.getMonday().split(":")[0]));
            TP0.setMinute(Integer.parseInt(week.getMonday().split(":")[1]));
        }
        if (!week.getTuesday().equals(none)) {
            TP1.setHour(Integer.parseInt(week.getTuesday().split(":")[0]));
            TP1.setMinute(Integer.parseInt(week.getTuesday().split(":")[1]));
        }
        if (!week.getWednesday().equals(none)) {
            TP2.setHour(Integer.parseInt(week.getWednesday().split(":")[0]));
            TP2.setMinute(Integer.parseInt(week.getWednesday().split(":")[1]));
        }
        if (!week.getThursday().equals(none)) {
            TP3.setHour(Integer.parseInt(week.getThursday().split(":")[0]));
            TP3.setMinute(Integer.parseInt(week.getThursday().split(":")[1]));
        }
        if (!week.getFriday().equals(none)) {
            TP4.setHour(Integer.parseInt(week.getFriday().split(":")[0]));
            TP4.setMinute(Integer.parseInt(week.getFriday().split(":")[1]));
        }
        if (!week.getSaturday().equals(none)) {
            TP5.setHour(Integer.parseInt(week.getSaturday().split(":")[0]));
            TP5.setMinute(Integer.parseInt(week.getSaturday().split(":")[1]));
        }
        if (!week.getSunday().equals(none)) {
            TP6.setHour(Integer.parseInt(week.getSunday().split(":")[0]));
            TP6.setMinute(Integer.parseInt(week.getSaturday().split(":")[1]));
        }
    }
}
