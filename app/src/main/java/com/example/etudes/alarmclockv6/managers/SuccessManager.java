package com.example.etudes.alarmclockv6.managers;

import com.example.etudes.alarmclockv6.services.NightService;
import com.example.etudes.alarmclockv6.services.SuccessService;
import com.example.etudes.alarmclockv6.services.modeles.Night;
import com.example.etudes.alarmclockv6.services.modeles.Success;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Florian on 21/11/2017.
 */

//TODO : Test it

public class SuccessManager {

    private static SuccessService service= null;

    private static void init(){
        if(service==null) service = SuccessService.getInstance();
    }

    public static void checkSuccesses(){
        init();
    }

    public static void checkNightSuccesses(){
        init();
        List<Success>  successes = service.getSuccessByKey("NIGHT");
        for (Success success: successes) {
            String id = success.getId().split(" ")[1];
            switch (id){
                case "0": whiteNight(success);
                    break;
            }
        }
    }


    public static void badNight(){
        init();
        List<Success> successes = service.getSuccessByKey("NIGHT");
        for (Success success : successes){
            success.setAdvancement(0);
        }
    }

    public static void sleptWell(){
        sleptWellOneNight();
        sleptWellAWeek();
        sleptWellAMonth();
    }

    private static void sleptWellOneNight(){
        init();
        List<Success> successes = service.getSuccessByKey("NIGHT 1");
        if(successes!=null)successes.get(0).setFinished(true);
    }


    private static void sleptWellAWeek(){
        init();
        List<Success> successes = service.getSuccessByKey("NIGHT 7");
        if(successes!=null){
            successes.get(0).setAdvancement(successes.get(0).getAdvancement()+1);
            if(successes.get(0).getAdvancement()>=7) successes.get(0).setFinished(true);
        }
    }


    private static void sleptWellAMonth(){
        init();
        List<Success> successes = service.getSuccessByKey("NIGHT 31");
        if(successes!=null){
            successes.get(0).setAdvancement(successes.get(0).getAdvancement()+1);
            if(successes.get(0).getAdvancement()>31) successes.get(0).setFinished(true);
        }
    }

    private static void whiteNight(Success success){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,-1);
        Night lastNight = NightService.getInstance().getNight(new SimpleDateFormat(Night.DATE_FORMAT).format(calendar.getTime()));
        if( lastNight.getGotToBedReal().equals("") && !lastNight.getWakeUpReal().equals("")){
            success.setFinished(true);
        }
    }

    public static void fasterThanLight(){
        init();
        List<Success> successes = service.getSuccessByKey("GAME 5");
        if(successes!=null)successes.get(0).setFinished(true);
    }

    public static void rageQuit(){
        firstGameRageQuit();
        gameRageQuitFiveTimes();
    }

    private static void firstGameRageQuit(){
        init();
        List<Success> successes = service.getSuccessByKey("GAME -1");
        if(successes!=null)successes.get(0).setFinished(true);
    }


    private static void gameRageQuitFiveTimes(){
        init();
        List<Success> successes = service.getSuccessByKey("GAME -5");
        if(successes!=null){
            successes.get(0).setAdvancement(successes.get(0).getAdvancement()+1);
            if(successes.get(0).getAdvancement()>=5) successes.get(0).setFinished(true);
        }
    }


    public static void gameBeaten(){
        gameBeatenForFirstTime();
        gameBeatenSevenTimes();
        gameBeaten31Times();
    }


    private static void gameBeatenForFirstTime(){
        init();
        List<Success> successes = service.getSuccessByKey("GAME 1");
        if(successes!=null) successes.get(0).setFinished(true);
    }

    private static void gameBeatenSevenTimes(){
        init();
        List<Success> successes = service.getSuccessByKey("GAME 7");
        if(successes!=null){
            successes.get(0).setAdvancement(successes.get(0).getAdvancement()+1);
            if(successes.get(0).getAdvancement()>=7) successes.get(0).setFinished(true);
        }
    }


    private static void gameBeaten31Times(){
        init();
        List<Success> successes = service.getSuccessByKey("GAME 31");
        if(successes!=null){
            successes.get(0).setAdvancement(successes.get(0).getAdvancement()+1);
            if(successes.get(0).getAdvancement()>=31) successes.get(0).setFinished(true);
        }
    }


    public static void firstTimeToParameter(){
        init();
        List<Success> successes = service.getSuccessByKey("PARAMS 1");
        if(successes!=null){
            successes.get(0).setFinished(true);
        }
    }

    public static void fourHoursOnly(){
        init();
        List<Success> successes = service.getSuccessByKey("PARAMS 4");
        if(successes!=null){
            successes.get(0).setFinished(true);
        }
    }

    public static void tenHoursSleep(){
        init();
        List<Success> successes = service.getSuccessByKey("PARAMS 10");
        if(successes!=null){
            successes.get(0).setFinished(true);
        }
    }


    public static void parametersFifteenTimes(){
        init();
        List<Success> successes = service.getSuccessByKey("PARAMS 15");
        if(successes!=null){
            successes.get(0).setAdvancement(successes.get(0).getAdvancement()+1);
            if(successes.get(0).getAdvancement()>=15) successes.get(0).setFinished(true);
        }
    }

    public static void lateOneNight(){
        List<Success> successes = service.getSuccessByKey("LATE 1");
        if(successes!=null){
            successes.get(0).setFinished(true);
        }
    }

    public static void lateFiveNights(){
        List<Success> successes = service.getSuccessByKey("LATE 5");
        if(successes!=null) {
            successes.get(0).setFinished(true);
            successes.get(0).setAdvancement(successes.get(0).getAdvancement() + 1);
            if (successes.get(0).getAdvancement() >= 5) successes.get(0).setFinished(true);
        }
    }


}
