package com.example.etudes.alarmclockv6.Database;

import com.example.etudes.alarmclockv6.services.SuccessService;
import com.example.etudes.alarmclockv6.services.modeles.Success;

/**
 * Created by Florian on 18/11/2017.
 */

public class SuccessPopulator {
    private SuccessService service;


    public SuccessPopulator(){
        service = SuccessService.getInstance();
        if(service.getSucces().size()<1)
            insertSuccesses();
    }

    private void insertSuccesses(){
        service.createSuccess(new Success("NIGHT 1","Like a baby", "Have your first good night",0,5,false));
        service.createSuccess(new Success("NIGHT 7","7 is pretty", "You slept well 7 days straight",0,10,false));
        service.createSuccess(new Success("NIGHT 31","Better than ever", "You slept well 31 days straight",0,20,false));
        service.createSuccess(new Success("LATE 1","This won't happen again, I promise", "You were late",0,2,false));
        service.createSuccess(new Success("LATE 5","There must be something wrong...", "You were late 5 times",0,0,false));
        service.createSuccess(new Success("GAME 1","First time", "You woke up",0,5,false));
        service.createSuccess(new Success("GAME 5","Faster than light", "You beat the game in less than 5 seconds",0,2,false));
        service.createSuccess(new Success("GAME 7","Starting to be a habit", "7 games beaten",0,5,false));
        service.createSuccess(new Success("GAME 31","Master of alarms", "31 alarms have bitten the dust !",0,10,false));
        service.createSuccess(new Success("GAME -1","I won't let you down, master !", "You have had a hard time to wake up today",0,5,false));
        service.createSuccess(new Success("GAME -5","Sleeping beauty", "Remove this needle, and let us start anew !",0,0,false));
        service.createSuccess(new Success("NIGHT 0","What is sleep ?", "Don't mix bravery and stubbornness",0,0,false));
        service.createSuccess(new Success("PARAMS 1","Nice to meet you, master !", "Setup your alrams for the first time",0,5,false));
        service.createSuccess(new Success("PARAMS 15","Indecisive", "Is the man who set his alarms 15 times",0,5,false));
        service.createSuccess(new Success("PARAMS 4","Sleeping is for the weak.", "Four hours only ? You are the master, master.",0,0,false));
        service.createSuccess(new Success("PARAMS 10","A good night for a good day", "10 hours of sleep",0,0,false));
    }

}
