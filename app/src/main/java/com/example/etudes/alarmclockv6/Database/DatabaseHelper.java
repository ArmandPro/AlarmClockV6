package com.example.etudes.alarmclockv6.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import javax.crypto.spec.DHGenParameterSpec;

/**
 * Created by Florian on 01/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "AlarmClock";
    private static final int DATABASE_VERSION = 1;



    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseConstants.CREATE_TABLE_NIGHT);
        sqLiteDatabase.execSQL(DatabaseConstants.CREATE_TABLE_WEEK);
        sqLiteDatabase.execSQL(DatabaseConstants.CREATE_TABLE_HABITS);
        Log.d("DBHelper","Tables created !");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),"Upgrading database from Version-"+oldVersion+" to Version-"+newVersion+", destroying all the old data");
        sqLiteDatabase.execSQL(DatabaseConstants.DROP_TABLE_NIGHT);
        sqLiteDatabase.execSQL(DatabaseConstants.DROP_TABLE_WEEK);
        sqLiteDatabase.execSQL(DatabaseConstants.DROP_TABLE_HABITS);
        onCreate(sqLiteDatabase);
    }
}
