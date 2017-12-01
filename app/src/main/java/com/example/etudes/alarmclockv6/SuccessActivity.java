package com.example.etudes.alarmclockv6;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.etudes.alarmclockv6.services.SuccessService;
import com.example.etudes.alarmclockv6.services.modeles.Success;

import java.util.List;

public class SuccessActivity extends AppCompatActivity {

    private List<Success> successes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        LinearLayout layout = findViewById(R.id.successList);
        successes = SuccessService.getInstance().getSuccesses();
        for(Success success : successes){
            generateSuccessTab(success,layout);
        }
    }


    private void generateSuccessTab(Success success, LinearLayout layout){
        LinearLayout box = new LinearLayout(getApplicationContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setGravity(Gravity.LEFT);
        box.setPadding(20,2,2,2);

        TextView title = new TextView(getApplicationContext());
        title.setText(success.getName());
        title.setTextColor(Color.BLACK);
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);

        TextView desc = new TextView(getApplicationContext());
        desc.setText(success.getDescription());
        desc.setTextColor(Color.argb(255,16,16,16));
        desc.setTextSize(TypedValue.COMPLEX_UNIT_SP,15);


        if(success.isFinished()){
            box.setBackgroundColor(getResources().getColor(R.color.cardview_shadow_start_color));
            title.setTextColor(Color.WHITE);
            title.setTextSize(35);
            desc.setTextColor(Color.WHITE);
            desc.setTextSize(25);

        }else {
            box.setBackgroundColor(getResources().getColor(R.color.black_overlay));


        }


        box.addView(title);
        box.addView(desc);
        layout.addView(box,params);
    }
}
