package com.example.etudes.alarmclockv6;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.etudes.alarmclockv6.services.WeekService;
import com.example.etudes.alarmclockv6.services.modeles.Week;


public class ScrollingActivity extends AppCompatActivity {

    private TimePicker TP0, TP1, TP2, TP3, TP4, TP5, TP6;
    private Switch s0,s1,s2,s3,s4,s5,s6;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(23);
        numberPicker.setValue(8);


        TP0 = (TimePicker) findViewById(R.id.timePicker0);
        TP1 = (TimePicker) findViewById(R.id.timePicker1);
        TP2 = (TimePicker) findViewById(R.id.timePicker2);
        TP3 = (TimePicker) findViewById(R.id.timePicker3);
        TP4 = (TimePicker) findViewById(R.id.timePicker4);
        TP5 = (TimePicker) findViewById(R.id.timePicker5);
        TP6 = (TimePicker) findViewById(R.id.timePicker6);

        s0 = (Switch) findViewById(R.id.switch0);
        s1 = (Switch) findViewById(R.id.switch1);
        s2 = (Switch) findViewById(R.id.switch2);
        s3 = (Switch) findViewById(R.id.switch3);
        s4 = (Switch) findViewById(R.id.switch4);
        s5 = (Switch) findViewById(R.id.switch5);
        s6 = (Switch) findViewById(R.id.switch6);

        loadWeek();




        Button buttonSetWeek = (Button) findViewById(R.id.buttonSetWeek);

        buttonSetWeek.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                Context context = getApplicationContext();

                WeekService weekService = WeekService.getInstance();
                Week week = weekService.getWeek();
                String none = "none";
                if (s0.isChecked()){
                    week.setMonday(TP0.getHour() + ":" + TP0.getMinute());
                }else{
                    week.setMonday(none);
                }
                if (s0.isChecked()){
                    week.setTuesday(TP1.getHour() + ":" + TP1.getMinute());
                }else{
                    week.setTuesday(none);
                }
                if (s0.isChecked()) {
                    week.setWednesday(TP2.getHour() + ":" + TP2.getMinute());
                }else{
                    week.setWednesday(none);
                }
                if (s0.isChecked()){
                    week.setThursday(TP3.getHour() + ":" + TP3.getMinute());
                }else {
                    week.setThursday(none);
                }
                if (s0.isChecked()) {
                    week.setFriday(TP4.getHour() + ":" + TP4.getMinute());
                }else{
                    week.setFriday(none);
                }
                if (s0.isChecked()) {
                    week.setSaturday(TP5.getHour() + ":" + TP5.getMinute());
                }else{
                    week.setSaturday(none);
                }
                if (s0.isChecked()) {
                    week.setSunday(TP6.getHour() + ":" + TP6.getMinute());
                }else{
                    week.setSunday(none);
                }
                //Toast toast1=Toast.makeText(getApplicationContext(),day2,Toast.LENGTH_LONG);
                //toast1.setMargin(50,50);
                //toast1.show();


                //Set or cancel all alarm
                GlobalAlarmManager globalAlarmManager = new GlobalAlarmManager();
                globalAlarmManager.updateAlarm(getApplicationContext());

                //Set or cancel all "Go to bed" notification
                globalAlarmManager.updateTimeToGoBed(getApplicationContext());

                Toast.makeText(getApplicationContext(), "Week succesfully setup", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void loadWeek() {
        Week week = WeekService.getInstance().getWeek();
        String none = "none";
        if (!week.getMonday().equals(none)) {
            TP0.setHour(Integer.parseInt(week.getMonday().split(":")[0]));
            TP0.setMinute(Integer.parseInt(week.getMonday().split(":")[1]));
        }else {
            s0.setChecked(false);
        }
        if (!week.getTuesday().equals(none)) {
            TP1.setHour(Integer.parseInt(week.getTuesday().split(":")[0]));
            TP1.setMinute(Integer.parseInt(week.getTuesday().split(":")[1]));
        }else{
            s1.setChecked(false);
        }
        if (!week.getWednesday().equals(none)) {
            TP2.setHour(Integer.parseInt(week.getWednesday().split(":")[0]));
            TP2.setMinute(Integer.parseInt(week.getWednesday().split(":")[1]));
        }else{
            s2.setChecked(false);
        }

        if (!week.getThursday().equals(none)) {
            TP3.setHour(Integer.parseInt(week.getThursday().split(":")[0]));
            TP3.setMinute(Integer.parseInt(week.getThursday().split(":")[1]));
        }else {
            s3.setChecked(false);
        }
        if (!week.getFriday().equals(none)) {
            TP4.setHour(Integer.parseInt(week.getFriday().split(":")[0]));
            TP4.setMinute(Integer.parseInt(week.getFriday().split(":")[1]));
        }else {
            s4.setChecked(false);
        }
        if (!week.getSaturday().equals(none)) {
            TP5.setHour(Integer.parseInt(week.getSaturday().split(":")[0]));
            TP5.setMinute(Integer.parseInt(week.getSaturday().split(":")[1]));
        }else{
            s5.setChecked(false);
        }
        if (!week.getSunday().equals(none)) {
            TP6.setHour(Integer.parseInt(week.getSunday().split(":")[0]));
            TP6.setMinute(Integer.parseInt(week.getSaturday().split(":")[1]));
        }else{
            s6.setChecked(false);
        }
    }
}
