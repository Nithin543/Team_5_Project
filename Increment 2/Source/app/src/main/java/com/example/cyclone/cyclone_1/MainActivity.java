package com.example.cyclone.cyclone_1;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.security.AccessController.getContext;


public class MainActivity extends AppCompatActivity {


    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        try {
            get();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


public void get() throws PackageManager.NameNotFoundException {
    //  Creating an object for UsageStates class(Contains usage statistics for an app package for specific time range)
    ImageView imageView = (ImageView)findViewById(R.id.image1);

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


            } catch (final PackageManager.NameNotFoundException e) {}

            Drawable icon = packageManager.getApplicationIcon("com.example.cyclone.cyclone_1");
            imageView.setImageDrawable(icon);


            AppName = (String)((applicationInfo != null) ? packageManager.getApplicationLabel(applicationInfo) : "???");

            Log.i("BAC", "PackageName :   " + PackageName + " Time : " + AppUsageTime );


// d3 js and pi chart
        }
    }


}


}
