package com.example.etudes.alarmclockv6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.modeles.Night;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian on 30/11/2017.
 */

public class LastWeekView extends View {

    private Canvas canvas;
    private Rect midnightLine;
    private List<Rect> reals, estimated;
    private List<Night> nights;

    public LastWeekView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        try{
            //NightPopulator.getInstance(context).populate();
        }catch (Exception e){
            Log.d("GRAPHIC",e.getStackTrace().toString());
        }
        DatabaseManager.getInstance(context).displayNights();
        nights = NightService.getInstance().getLastNights();
        reals = new ArrayList<>();
        estimated = new ArrayList<>();
    }



    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        this.canvas = canvas;
        midnightLine = new Rect();
        midnightLine.set(0,(int)(0.75*canvas.getHeight()/2)+14,canvas.getWidth(),16+(int)(0.75*canvas.getHeight()/2));
        Paint midnightColor = new Paint();
        midnightColor.setColor(getResources().getColor(R.color.cardview_shadow_start_color));
        midnightColor.setStyle(Paint.Style.FILL);
        canvas.drawRect(midnightLine,midnightColor);
        if(nights!=null)generateNights();
    }


    private void generateNights(){
        int max = 0;
        int  min = 999999999;
        int count = nights.size();
        List<Integer> realsDim = new ArrayList<>();
        List<Integer> estimatedDim = new ArrayList<>();
        for(Night night : nights){
            try {
                long start = new SimpleDateFormat(Night.DATE_HOUR_FORMAT).parse(night.getGotToBedReal()).getTime();
                long end = new SimpleDateFormat(Night.DATE_HOUR_FORMAT).parse(night.getWakeUpReal()).getTime();
                int height = (int)(end -start)/60000;
                realsDim.add(height);
                if(height>max) max = height;
                if(height<min) min = height;
                start = new SimpleDateFormat(Night.DATE_HOUR_FORMAT).parse(night.getGoToBedEstimated()).getTime();
                end = new SimpleDateFormat(Night.DATE_HOUR_FORMAT).parse(night.getWakeUpEstimated()).getTime();
                height = (int)(end-start)/60000;
                estimatedDim.add(height);
                if(height>max) max = height;
                if(height<min) min = height;

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Paint blue = new Paint();
        blue.setColor(getResources().getColor(R.color.colorPrimary));
        blue.setStyle(Paint.Style.FILL);

        Paint pink = new Paint();
        pink.setColor(getResources().getColor(R.color.colorAccent));
        pink.setStyle(Paint.Style.FILL);


        for(int i = 0; i<count; i++){
            int left = (int)((i+1)*canvas.getWidth()/(float)(count+1));
            int right= left+15;
            int top = 15+(int)(0.75*canvas.getHeight()*((float)estimatedDim.get(i)/max));
            int bottom =15+(int)(0.75*canvas.getHeight()-top);
            //Log.d("GRAPHIC","left : "+left+"\t right : "+right+"\n top :"+top+"\t bottom :"+bottom);
            estimated.add(new Rect(left, bottom, right, top));

            left = (int)((i+1)/(float)(count+1)*canvas.getWidth())+10;
            right= left+15;
            top = 15+(int)(0.75*canvas.getHeight()*((float)realsDim.get(i)/max));
            bottom = 15+(int)(0.75*canvas.getHeight()-top);
            //Log.d("GRAPHIC","left : "+left+"\t right : "+right+"\n top :"+top+"\t bottom :"+bottom);
            reals.add(new Rect(left, bottom, right, top));
        }


        //canvas.drawRect(new Rect(0,0,canvas.getWidth(),canvas.getHeight()/2), blue);

        for(Rect rect : estimated){
            canvas.drawRect(rect,blue);
        }
        for(Rect rect : reals){
            canvas.drawRect(rect,pink);
        }

    }




}
