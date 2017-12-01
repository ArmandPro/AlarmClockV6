package com.example.etudes.alarmclockv6.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.etudes.alarmclockv6.R;

/**
 * Created by Florian on 01/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "AlarmClock";
    private static final int DATABASE_VERSION = 5;
    private static Context context = null;


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(context.getString(R.string.CREATE_TABLE_NIGHT));
        sqLiteDatabase.execSQL(context.getString(R.string.CREATE_TABLE_WEEK));
        sqLiteDatabase.execSQL(context.getString(R.string.CREATE_TABLE_HABITS));
        sqLiteDatabase.execSQL(context.getString(R.string.CREATE_TABLE_SUCCESS));
        Log.d("DBHelper","Tables created !");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),"Upgrading database from Version-"+oldVersion+" to Version-"+newVersion+", destroying all the old data");
        sqLiteDatabase.execSQL(context.getString(R.string.DROP_TABLE_NIGHT));
        sqLiteDatabase.execSQL(context.getString(R.string.DROP_TABLE_WEEK));
        sqLiteDatabase.execSQL(context.getString(R.string.DROP_TABLE_HABITS));
        sqLiteDatabase.execSQL(context.getString(R.string.DROP_TABLE_SUCCESS));
        onCreate(sqLiteDatabase);
    }
}
