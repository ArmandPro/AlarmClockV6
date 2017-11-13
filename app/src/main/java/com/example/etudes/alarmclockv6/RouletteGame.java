package com.example.etudes.alarmclockv6;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.etudes.alarmclockv6.AbstractGame;
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

        goalTV = findViewById(R.id.goal);
        actualTV = findViewById(R.id.actualScore);
        roulette = findViewById(R.id.roulette);
        roulette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playersValue+= roulette.getText();
            }
        });
    }


    private void addValue(String value){
        playersValue+=value;
        actualTV.setText(playersValue);
        if(goal.startsWith(playersValue)){
            actualTV.setTextColor(Color.GREEN);
        }else{
            actualTV.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void initGame(){
        goal = ""+(new Random().nextInt(900)+100);
        goalTV.setText(goal);
        initTimer();
        launchCounter();
    }

    private void launchCounter(){
        currentNumber= -1;
        rouletteTimer = new CountDownTimer(5000,500) {
            @Override
            public void onTick(long l) {
                currentNumber++;
                roulette.setText(""+currentNumber);
            }

            @Override
            public void onFinish() {
                rouletteTimer.start();
            }
        };
    }
}
