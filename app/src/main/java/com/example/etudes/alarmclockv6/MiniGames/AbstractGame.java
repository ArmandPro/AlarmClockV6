package com.example.etudes.alarmclockv6.MiniGames;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.etudes.alarmclockv6.R;
import com.example.etudes.alarmclockv6.managers.SuccessManager;
import com.example.etudes.alarmclockv6.services.NightService;

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
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void onPause(){
        super.onPause();
        timer.cancel();
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
        NightService.getInstance().wokeUp();
        if(counter<=5){
            SuccessManager.fasterThanLight();
            Log.d(GAME_NAME,"Faster than light !");
        }
        SuccessManager.gameBeaten();
        //finish();
        //System.exit(0);
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }

    protected void displayExplanation(){
        Toast.makeText(getApplicationContext(),EXPLAINATION,Toast.LENGTH_SHORT).show();
    }

    protected void initTimer() {
        counter = 0;
        timer = new CountDownTimer(15000, 150) {
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
