package com.example.etudes.alarmclockv6.MiniGames;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.etudes.alarmclockv6.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MatrixGame extends AbstractGame {

    private ArrayList<Button> buttons;
    private ArrayList<Integer> order;
    private int currentValueToMatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minigame_matrix);
        GAME_NAME = "Count to 9 !";
        EXPLAINATION = "Tap the numbers from 1 to 9";
        buttons = new ArrayList<>();
        buttons.add((Button) findViewById(R.id.button11));
        buttons.add((Button) findViewById(R.id.button12));
        buttons.add((Button) findViewById(R.id.button13));
        buttons.add((Button) findViewById(R.id.button21));
        buttons.add((Button) findViewById(R.id.button22));
        buttons.add((Button) findViewById(R.id.button23));
        buttons.add((Button) findViewById(R.id.button31));
        buttons.add((Button) findViewById(R.id.button32));
        buttons.add((Button) findViewById(R.id.button33));
        for (Button button : buttons) {
            final Button current = button;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isOk(Integer.parseInt(current.getText().toString()))) {
                        current.setClickable(false);
                        current.setBackgroundColor(Color.parseColor("#44A08D"));
                        isGameFinised();
                    }
                }
            });
        }
        order = new ArrayList<>();
        for (int i = 1; i < 10; i++) order.add(i);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        initTimer();
        displayExplanation();
    }

    @Override
    protected void initGame() {
        timer.start();
        currentValueToMatch = 1;
        for (Button button : buttons) {
            button.setBackgroundColor(Color.parseColor("#093637"));
            button.setTextColor(Color.WHITE);
            button.setClickable(true);
        }
        Collections.shuffle(order);
        int i;
        for (i = 0; i < 9; i++) {
            buttons.get(i).setText(order.get(i).toString());
        }
    }


    private boolean isOk(int i) {
        if (i == currentValueToMatch) {

            currentValueToMatch++;
            return true;
        } else {
            hasFailed();
            return false;
        }
    }

    private void isGameFinised() {
        if (currentValueToMatch == 10) {
            timer.cancel();
            gameEnded();
        }
    }

}
