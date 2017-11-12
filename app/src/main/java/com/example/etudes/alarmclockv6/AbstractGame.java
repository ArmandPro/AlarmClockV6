package com.example.etudes.alarmclockv6;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.etudes.alarmclockv6.R;

public class AbstractGame extends AppCompatActivity {

    protected CountDownTimer timer;
    protected int counter;
    protected ProgressBar progressBar;
    protected static int DEFAULT_COUNTDOWN = 15;
    protected static String EXPLAINATION="Beat the game in less than "+DEFAULT_COUNTDOWN+" seconds.";
    protected static String GAME_NAME = "ABSTRACT GAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract_game);
        initTimer();
    }

    protected void initGame(){

    }

    protected void hasFailed(){
        timer.cancel();
        initTimer();
        progressBar.setProgress(0);
        initGame();
    }

    protected void displayExplanation(){
        Snackbar.make(findViewById(android.R.id.content), EXPLAINATION, Snackbar.LENGTH_LONG)
                .show();
    }

    protected void initTimer() {
        counter = 0;
        timer = new CountDownTimer(15000, 100) {
            @Override
            public void onTick(long l) {
                counter++;
                progressBar.setProgress(counter);
            }
            @Override
            public void onFinish() {
                Log.d("MATRIX GAME", "TIMED OUT");
                hasFailed();
            }
        };
    }
}
