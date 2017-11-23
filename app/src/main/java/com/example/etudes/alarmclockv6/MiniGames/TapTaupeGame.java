package com.example.etudes.alarmclockv6.MiniGames;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;

import com.example.etudes.alarmclockv6.R;

public class TapTaupeGame extends AbstractGame {

    private CountDownTimer generator;
    private Button mole1, mole2, mole3,mole4;
    private CountDownTimer randomGenerator;
    private int winWidth, winHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_taupe_game);
        mole1 = findViewById(R.id.mole1);
        mole2 = findViewById(R.id.mole2);
        mole3 = findViewById(R.id.mole3);
        mole4 = findViewById(R.id.mole4);
        winWidth = findViewById(R.id.hitmoleLayout).getWidth();
        winHeight= findViewById(R.id.hitmoleLayout).getHeight();
    }

    @Override
    public void initGame(){
        initTimer();
        timer.start();
    }


    private void initGenerator(){

        randomGenerator = new CountDownTimer(100000,7) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

            }
        };
    }





}
