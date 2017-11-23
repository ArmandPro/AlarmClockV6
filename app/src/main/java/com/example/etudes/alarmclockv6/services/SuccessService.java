package com.example.etudes.alarmclockv6.services;

import com.example.etudes.alarmclockv6.Database.DatabaseManager;
import com.example.etudes.alarmclockv6.services.modeles.Success;

import java.util.List;

/**
 * Created by Florian on 18/11/2017.
 */

public class SuccessService {

    private static SuccessService instance = null;
    private DatabaseManager database;
    private SuccessService(){
        database = DatabaseManager.getInstance(null);

    }
    public static SuccessService getInstance(){
        if(instance==null) instance = new SuccessService();
        return instance;
    }

    public List<Success> getSucces(){
        return database.getSuccesses();
    }

    public Success createSuccess(Success success){
        database.insertSuccess(success);
        return  success;
    }

    public Success updateSuccess(Success success){
        database.updateSucces(success);
        return success;
    }

    public List<Success> getSuccessByKey(String key){
        return database.getSuccessesByKey(key);
    }
}
