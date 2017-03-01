package com.example.cyclone.cyclone_1;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.graphics.drawable.InsetDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import android.app.ActivityManager;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get();
    }


public void get() {
    //  Creating an object for UsageStates class(Contains usage statistics for an app package for specific time range)
    Context context = this;
    // variable PackageName
    String PackageName = " ";
    // variables for calculating time
    long TimeInForGround = 0;

    int minutes = 0, hours = 0, seconds = 0;


    // Creating an Object for UsageStatesManager class( accesses for system time logs)
    UsageStatsManager mUsageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);

    long time = System.currentTimeMillis();


    List<UsageStats> stats = null;

    stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 2000 * 60, time);


    if (stats != null) {
        SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();

        for (UsageStats usageStats : stats) {

            TimeInForGround = usageStats.getTotalTimeInForeground();

            long y = usageStats.getFirstTimeStamp();



            PackageName = usageStats.getPackageName();


            minutes = (int) ((TimeInForGround / (1000 * 60)) % 60);

            seconds = (int) (TimeInForGround / 1000) % 60;

            hours = (int) ((TimeInForGround / (1000 * 60 * 60)) % 24);

            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = null;
            try {
                applicationInfo = packageManager.getApplicationInfo(PackageName, 0);
            } catch (final PackageManager.NameNotFoundException e) {}
            final String title = (String)((applicationInfo != null) ? packageManager.getApplicationLabel(applicationInfo) : "???");





            Log.i("BAC", "PackageName is     " + title +
                    "  " + hours + ":" + minutes + ":" + seconds);


// d3 js and hi chart
        }
    }


}


}
