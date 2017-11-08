package com.example.etudes.alarmclockv6;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.AbstractSequentialList;
import java.util.Calendar;
import com.example.etudes.alarmclockv6.Database.DatabaseManager;


public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final TimePicker TP0 = (TimePicker) findViewById(R.id.timePicker0) ;
        final TimePicker TP1 = (TimePicker) findViewById(R.id.timePicker1) ;
        final TimePicker TP2 = (TimePicker) findViewById(R.id.timePicker2) ;
        final TimePicker TP3 = (TimePicker) findViewById(R.id.timePicker3) ;
        final TimePicker TP4 = (TimePicker) findViewById(R.id.timePicker4) ;
        final TimePicker TP5 = (TimePicker) findViewById(R.id.timePicker5) ;
        final TimePicker TP6 = (TimePicker) findViewById(R.id.timePicker6) ;

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




                final Calendar calendar0 = Calendar.getInstance();
                final Calendar calendar1 = Calendar.getInstance();
                final Calendar calendar2 = Calendar.getInstance();
                final Calendar calendar3 = Calendar.getInstance();
                final Calendar calendar4 = Calendar.getInstance();
                final Calendar calendar5 = Calendar.getInstance();
                final Calendar calendar6 = Calendar.getInstance();


                calendar0.set(Calendar.HOUR_OF_DAY,TP0.getHour());
                calendar0.set(Calendar.MINUTE,TP0.getMinute());

                calendar1.set(Calendar.HOUR_OF_DAY,TP1.getHour());
                calendar1.set(Calendar.MINUTE,TP1.getMinute());

                calendar2.set(Calendar.HOUR_OF_DAY,TP2.getHour());
                calendar2.set(Calendar.MINUTE,TP2.getMinute());

                calendar3.set(Calendar.HOUR_OF_DAY,TP3.getHour());
                calendar3.set(Calendar.MINUTE,TP3.getMinute());

                calendar4.set(Calendar.HOUR_OF_DAY,TP4.getHour());
                calendar4.set(Calendar.MINUTE,TP4.getMinute());

                calendar5.set(Calendar.HOUR_OF_DAY,TP5.getHour());
                calendar5.set(Calendar.MINUTE,TP5.getMinute());

                calendar6.set(Calendar.HOUR_OF_DAY,TP6.getHour());
                calendar6.set(Calendar.MINUTE,TP6.getMinute());

                String day0 = "none", day1 = "none", day2 = "none", day3 = "none", day4 = "none", day5 = "none", day6 = "none";

                if(s0.isChecked())
                    day0 = calendar0.toString();

                if(s1.isChecked())
                    day1 = calendar1.toString();

                if(s2.isChecked())
                    day2 = calendar2.toString();

                if(s3.isChecked())
                    day3 = calendar3.toString();

                if(s4.isChecked())
                    day4 = calendar4.toString();

                if(s5.isChecked())
                    day5 = calendar5.toString();

                if(s6.isChecked())
                    day6 = calendar6.toString();




                WeekService weekService = new WeekService(getApplicationContext());

                weekService.createWeek(day0,day1,day2,day3,day4,day5,day6);




                Toast toast1=Toast.makeText(getApplicationContext(),day2,Toast.LENGTH_LONG);
                toast1.setMargin(50,50);
                toast1.show();














            }
        });




    }
}
