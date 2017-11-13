package com.example.etudes.alarmclockv6.MiniGames;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

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
    }

    protected void initGame(){

    }

    protected void hasFailed(){
        timer.cancel();
        initTimer();
        progressBar.setProgress(0);
        initGame();
    }


    protected void gameEnded(){
        timer.cancel();
        //NightService.getInstance().wokeUp();
        //System.exit(0);
    }

    protected void displayExplanation(){
        Toast.makeText(getApplicationContext(),EXPLAINATION,Toast.LENGTH_LONG).show();
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
                Log.d(GAME_NAME, "TIMED OUT");
                hasFailed();
            }
        };
    }
}