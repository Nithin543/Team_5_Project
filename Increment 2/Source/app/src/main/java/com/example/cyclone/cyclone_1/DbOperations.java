package com.example.cyclone.cyclone_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by chven on 3/1/2017.
 */

public class DbOperations extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;

    private static final String DB_Name = "AppInfo.db";

    public static final String App_Name = " App_Name ";

    public static final String App_Usage_Time = " App_Usage_Time ";

    public static final String Table_Name = " App_Info ";

    DbOperations(Context ctx){

        super(ctx,DB_Name,null,DB_VERSION);

        Log.d(" Database Operations "," Database Created .......... ");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE " + Table_Name + " ( " + App_Name + "TEXT,"

                    + App_Usage_Time + "INTEGER" +  " );" );

        Log.d(" Database Operations "," Table Created ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void insertData(String appname, int appusagetime){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(App_Name,appname);

        contentValues.put(App_Usage_Time,appusagetime);

        db.insert(Table_Name,null,contentValues);

    }
}
