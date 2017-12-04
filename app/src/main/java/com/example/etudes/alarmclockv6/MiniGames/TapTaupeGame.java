package com.example.etudes.alarmclockv6.MiniGames;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.etudes.alarmclockv6.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TapTaupeGame extends AbstractGame {

    private List<ImageButton> moles;
    private CountDownTimer randomGenerator;
    private int winWidth, winHeight, score;
    private TextView scoreView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EXPLAINATION = "Pop at least 15 clouds !";
        GAME_NAME = "Pop clouds";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_taupe_game);
        moles = new ArrayList<>();
        moles.add((ImageButton) findViewById(R.id.mole1));
        moles.add((ImageButton)findViewById(R.id.mole2));
        moles.add((ImageButton)findViewById(R.id.mole3));
        moles.add((ImageButton)findViewById(R.id.mole4));
        scoreView = findViewById(R.id.hitmoleScore);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        winWidth = displayMetrics.widthPixels;
        winHeight= displayMetrics.heightPixels;
        progressBar = findViewById(R.id.progress_bar);
        initMoles();
        displayExplanation();
    }

    @Override
    public void initGame(){
        initTimer();
        score = 0;
        scoreView.setText(""+score);
        timer.start();
        initGenerator();
        randomGenerator.start();
    }


    private void initGenerator(){

        randomGenerator = new CountDownTimer(100000,100) {
            @Override
            public void onTick(long l) {
                if(new Random().nextInt(100)>=75){
                    ImageButton mole =null;
                    for(int i = 0; i<moles.size();i++){
                        if(!(moles.get(i).getVisibility()==View.VISIBLE)){
                            mole = moles.get(i);
                        }
                    }
                    if(mole !=null){
                        mole.setX(new Random().nextFloat()*(winWidth-mole.getWidth())+1);
                        mole.setY(new Random().nextFloat()*(winHeight-mole.getHeight())+1);
                        mole.setVisibility(View.VISIBLE);
                    }else{
                        for(ImageButton m : moles){
                            if(m.getVisibility()== View.VISIBLE){
                                mole = m;
                            }
                        }
                        mole.setVisibility(View.INVISIBLE);
                    }
                    Collections.shuffle(moles);
                }
            }

            @Override
            public void onFinish() {

            }
        };
    }


    private void initMoles(){

        for( ImageButton mole : moles){
            final ImageButton molly = mole;
            mole.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    score += 1;
                    scoreView.setText(""+score);
                    molly.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    @Override
    public void hasFailed(){
        randomGenerator.cancel();
        super.hasFailed();
    }

    @Override
    public void initTimer() {
        counter = 0;
        timer = new CountDownTimer(15000, 100) {
            @Override
            public void onTick(long l) {
                counter++;
                progressBar.setProgress(counter);
            }
            @Override
            public void onFinish() {
                randomGenerator.cancel();
                if(score<15) hasFailed();
                else {
                    gameEnded();
                }
            }
        };
    }


    @Override
    public void onPause(){
        super.onPause();
        randomGenerator.cancel();
    }

}
