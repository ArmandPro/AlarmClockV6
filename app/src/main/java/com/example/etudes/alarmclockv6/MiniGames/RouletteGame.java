package com.example.etudes.alarmclockv6.MiniGames;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.etudes.alarmclockv6.AbstractGame;
import com.example.etudes.alarmclockv6.R;

public class RouletteGame extends AbstractGame {

    private String goal, playersValue;
    private Button roullette;
    private static String toto= null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette_game);

        roullette = (Button)findViewById(R.id.roulette);
        roullette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playersValue+= roullette.getText();
            }
        });
    }


    private void addValue(String value){
        playersValue+=value;

    }


}
