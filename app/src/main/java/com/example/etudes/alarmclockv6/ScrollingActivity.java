package com.example.etudes.alarmclockv6;

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

import com.example.etudes.alarmclockv6.services.HabitsService;
import com.example.etudes.alarmclockv6.services.WeekService;
import com.example.etudes.alarmclockv6.services.modeles.Night;
import com.example.etudes.alarmclockv6.services.modeles.Week;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ScrollingActivity extends AppCompatActivity {

    private List<TimePicker> timePickers;
    private List<Switch> switches;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        timePickers = new ArrayList<>();
        switches = new ArrayList<>();

        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(23);
        numberPicker.setValue(HabitsService.getInstance().getHabits().getSleepHoursPerNight());


        timePickers.add((TimePicker)findViewById(R.id.timePicker0));
        timePickers.add((TimePicker)findViewById(R.id.timePicker1));
        timePickers.add((TimePicker)findViewById(R.id.timePicker2));
        timePickers.add((TimePicker)findViewById(R.id.timePicker3));
        timePickers.add((TimePicker)findViewById(R.id.timePicker4));
        timePickers.add((TimePicker)findViewById(R.id.timePicker5));
        timePickers.add((TimePicker)findViewById(R.id.timePicker6));

        switches.add((Switch) findViewById(R.id.switch0));
        switches.add((Switch) findViewById(R.id.switch1));
        switches.add((Switch) findViewById(R.id.switch2));
        switches.add((Switch) findViewById(R.id.switch3));
        switches.add((Switch) findViewById(R.id.switch4));
        switches.add((Switch) findViewById(R.id.switch5));
        switches.add((Switch) findViewById(R.id.switch6));
        loadWeek();

        Button buttonSetWeek = (Button) findViewById(R.id.buttonSetWeek);

        buttonSetWeek.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat(Night.HOUR_FORMAT);
                Calendar calendar = Calendar.getInstance();
                WeekService weekService = WeekService.getInstance();
                Week week = weekService.getWeek();
                String none = "none";
                for( int i = 0; i<timePickers.size();i++){
                    if(switches.get(i).isChecked()){
                        calendar.set(Calendar.HOUR_OF_DAY,timePickers.get(i).getHour());
                        calendar.set(Calendar.MINUTE,timePickers.get(i).getMinute());
                        week.setXdayTime(i, sdf.format(calendar.getTime()));
                    }else {
                        week.setXdayTime(i,none);
                    }
                }
                Toast.makeText(getApplicationContext(), "Week succesfully setup", Toast.LENGTH_SHORT).show();


                //set or cancel alarm and notification
                GlobalAlarmManager globalAlarmManager = new GlobalAlarmManager();
                globalAlarmManager.updateAlarm(getApplicationContext());
                globalAlarmManager.updateTimeToGoBed(getApplicationContext());

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void loadWeek() {
        Week week = WeekService.getInstance().getWeek();
        String none = "none";
        for( int i = 0; i< timePickers.size();i++){
            String time = week.getXdayTime(i);
            if(!time.equals(none)){
                timePickers.get(i).setHour(Integer.parseInt(time.split(":")[0]));
                timePickers.get(i).setMinute(Integer.parseInt(time.split(":")[1]));
            }else {
                switches.get(i).setChecked(false);
            }
        }


    }
}
