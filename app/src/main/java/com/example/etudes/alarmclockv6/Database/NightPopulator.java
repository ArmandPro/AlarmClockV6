package com.example.etudes.alarmclockv6.Database;

import android.content.Context;

import com.example.etudes.alarmclockv6.services.modeles.Night;

/**
 * Created by Florian on 30/11/2017.
 */

public class NightPopulator {
    private static NightPopulator instance = null;
    private static DatabaseManager database = null;
    private NightPopulator(Context context){
        database = DatabaseManager.getInstance(context);
    }

    public static NightPopulator getInstance(Context context){
        if(instance==null) instance = new NightPopulator(context);
        return instance;
    }

    public void populate(){
        database.insertNight(new Night(101,"2017/11/27-22:00","2017/11/27-22:05","2017/11/28-06:00","2017/11/28-06:03",true,false));
        database.insertNight(new Night(102,"2017/11/29-00:00","2017/11/29-00:10","2017/11/29-08:00","2017/11/29-08:01",true,false));
        database.insertNight(new Night(103,"2017/11/29-23:30","2017/11/29-23:22","2017/11/30-07:30","2017/11/30-07:33",true,false));
        database.insertNight(new Night(104,"2017/11/30-23:30","2017/11/30-23:41","2017/12/01-07:30","2017/12/01-07:45",false,true));
        database.insertNight(new Night(105,"2017/12/02-00:00","2017/12/01-23:55","2017/12/02-08:00","2017/12/02-08:02",true,false));
        database.insertNight(new Night(106,"2017/12/02-22:00","2017/12/02-22:21","2017/12/03-06:00","2017/12/03-06:03",false,false));
        database.insertNight(new Night(107,"2017/12/03-23:00","2017/12/03-23:10","2017/12/04-07:00","2017/12/04-07:05",true,true));
    }
}
