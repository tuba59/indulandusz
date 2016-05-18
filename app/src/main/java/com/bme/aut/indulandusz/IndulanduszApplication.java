package com.bme.aut.indulandusz;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.orm.SugarApp;

public class IndulanduszApplication extends SugarApp {

    public static IndulanduszApplicationComponent injector;
    private Tracker mTracker;

    @Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerIndulanduszApplicationComponent.builder().
                        uIModule(
                                new UIModule(this)
                        ).build();
    }



    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
}