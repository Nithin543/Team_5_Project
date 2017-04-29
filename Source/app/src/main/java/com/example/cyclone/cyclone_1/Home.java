package com.example.cyclone.cyclone_1;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import com.example.cyclone.cyclone_1.data.LogReader;


public class Home extends AppCompatActivity {

    LogReader LogReader1 = new LogReader(this);

    private static final String TAG = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);


        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);

        if (isFirstRun) {
            try {
                get();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        } else {
            try {
                get1();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }


        displayDatabaseInfo();
    }

    public void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        int maxID= 0;
        SQLiteDatabase db = LogReader1.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(Time_Used) FROM logs", null);
        if (cursor.moveToFirst()) {
            do{
                maxID = cursor.getInt(0);
            }while(cursor.moveToNext());
        }

        TextView TotalView = (TextView) findViewById(R.id.Total);
        TotalView.setText(String.valueOf(maxID)+"Min");

        Cursor logCursor = db.rawQuery("SELECT rowid _id,name,Time_Used,app_Image FROM logs ORDER BY Time_Used DESC LIMIT 10", null);
        LogCursorAdapter logAdapter = new LogCursorAdapter(this, logCursor);
        ListView lvlogItems = (ListView) findViewById(R.id.Top_5);
        lvlogItems.setAdapter((logAdapter));
    }

    public void get() throws PackageManager.NameNotFoundException {
        //  Creating an object for UsageStates class(Contains usage statistics for an app package for specific time range)
        Context context = this;

        // variable for Package Name
        String PackageName = "";

        // variable for Application Name
        String AppName = "";

        // variables for calculating time
        long TimeInForGround = 0;

        int AppUsageTime = 0, hours = 0, seconds = 0;

        // Creating an Object for UsageStatesManager class( accesses for system time logs)
        UsageStatsManager mUsageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);

        long time = System.currentTimeMillis();


        List<UsageStats> stats = null;

        stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 24*60 * 60, time);


        if (stats != null) {
            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();

            for (UsageStats usageStats : stats) {

                TimeInForGround = usageStats.getTotalTimeInForeground();

                PackageName = usageStats.getPackageName();

                AppUsageTime = (int) ((TimeInForGround / (1000 * 60)) % 60);

                // seconds = (int) (TimeInForGround / 1000) % 60;

                // hours = (int) ((TimeInForGround / (1000 * 60 * 60)) % 24);

                PackageManager packageManager = context.getPackageManager();

                ApplicationInfo applicationInfo = null;

                try {

                    applicationInfo = packageManager.getApplicationInfo(PackageName, 0);

                } catch (final PackageManager.NameNotFoundException e) {
                }


                AppName = (String) ((applicationInfo != null) ? packageManager.getApplicationLabel(applicationInfo) : "???");

                Log.i("BAC", "PackageName :   " + AppName + " Time : " + AppUsageTime);

                Drawable icon = packageManager.getApplicationIcon(PackageName);
                Bitmap bitmap = ((BitmapDrawable) icon).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] image = stream.toByteArray();

                LogReader1.insertData(AppName, AppUsageTime, image);
            }


        }
    }

    public void get1() throws PackageManager.NameNotFoundException {
        //  Creating an object for UsageStates class(Contains usage statistics for an app package for specific time range)
        Context context = this;

        // variable for Package Name
        String PackageName = "";

        // variable for Application Name
        String AppName = "";

        // variables for calculating time
        long TimeInForGround = 0;

        int AppUsageTime = 0, hours = 0, seconds = 0;

        // Creating an Object for UsageStatesManager class( accesses for system time logs)
        UsageStatsManager mUsageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);

        long time = System.currentTimeMillis();


        List<UsageStats> stats = null;

        stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 2000 * 60, time);


        if (stats != null) {
            SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();

            for (UsageStats usageStats : stats) {

                TimeInForGround = usageStats.getTotalTimeInForeground();

                PackageName = usageStats.getPackageName();

                AppUsageTime = (int) ((TimeInForGround / (1000 * 60)) % 60);

                // seconds = (int) (TimeInForGround / 1000) % 60;

                // hours = (int) ((TimeInForGround / (1000 * 60 * 60)) % 24);

                PackageManager packageManager = context.getPackageManager();

                ApplicationInfo applicationInfo = null;

                try {

                    applicationInfo = packageManager.getApplicationInfo(PackageName, 0);

                } catch (final PackageManager.NameNotFoundException e) {
                }
                Drawable icon = packageManager.getApplicationIcon(PackageName);
                Bitmap bitmap = ((BitmapDrawable) icon).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] image = stream.toByteArray();

                AppName = (String) ((applicationInfo != null) ? packageManager.getApplicationLabel(applicationInfo) : "???");

                Log.i("BAC", "PackageName :   " + AppName + " Time : " + AppUsageTime);


                LogReader1.updateData(AppName, AppUsageTime, image);
            }
        }
    }
}