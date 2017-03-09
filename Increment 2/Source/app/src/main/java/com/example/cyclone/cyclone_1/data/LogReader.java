package com.example.cyclone.cyclone_1.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import static com.example.cyclone.cyclone_1.data.LogContract.logEntry.Coloumn_Name_Of_Application;
import static com.example.cyclone.cyclone_1.data.LogContract.logEntry.Coloumn_Time_Used;
import static com.example.cyclone.cyclone_1.data.LogContract.logEntry.Table_Name;

/**
 * Created by ALAAP on 3/2/2017.
 */

public class LogReader extends SQLiteOpenHelper {
    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "Logs";


    public LogReader(Context context){

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    public void onCreate(SQLiteDatabase db) {
         final String SQL_CREATE_ENTRIES = "CREATE TABLE " + Table_Name + "( "
                + Coloumn_Name_Of_Application + " TEXT ,"
                + Coloumn_Time_Used + " INTEGER " + " );";
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    public void insertData(String appname, int appusagetime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Coloumn_Name_Of_Application, appname);
        contentValues.put(Coloumn_Time_Used, appusagetime);
        db.insert(Table_Name, null, contentValues);
    }



    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}

