package com.example.etudes.alarmclockv6.MiniGames;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.etudes.alarmclockv6.R;

import java.util.Random;

public class RouletteGame extends AbstractGame {

    private String goal, playersValue;
    private Button roulette;
    private TextView goalTV, actualTV;
    private CountDownTimer rouletteTimer;
    private int currentNumber;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette_game);
        EXPLAINATION="Use the button to print the same number";
        progressBar = findViewById(R.id.progress_bar_roulette);
        goalTV = findViewById(R.id.goal);
        actualTV = findViewById(R.id.actualScore);
        roulette = findViewById(R.id.roulette);
        playersValue="";
        roulette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValue(roulette.getText().toString());
            }
        });
        displayExplanation();
    }


    @Override
    public void initGame(){
        goal = ""+(new Random().nextInt(900)+100);
        goalTV.setText(goal);
        initTimer();
        timer.start();
        launchCounter();
        rouletteTimer.start();
    }

    private void addValue(String value){
        playersValue+=value;
        actualTV.setText(playersValue);
        if(goal.startsWith(playersValue)){
            actualTV.setTextColor(Color.GREEN);
            if(goalTV.getText().toString().equals(playersValue)){
                rouletteTimer.cancel();

                gameEnded();
            }
        }else{
            actualTV.setTextColor(Color.RED);
            if(playersValue.length()>=3){
                hasFailed();
            }
        }

    }

    protected void hasFailed(){
        super.hasFailed();
        rouletteTimer.cancel();
        playersValue="";
        actualTV.setText("");
    }

    private void launchCounter(){
        currentNumber= -1;
        int speed = 5;
        rouletteTimer = new CountDownTimer(speed*1100,speed*100) {
            @Override
            public void onTick(long l) {
                currentNumber++;
                roulette.setText(""+currentNumber);
            }

            @Override
            public void onFinish() {
                currentNumber=-1;
                rouletteTimer.cancel();
                rouletteTimer.start();
            }
        };
    }
}
