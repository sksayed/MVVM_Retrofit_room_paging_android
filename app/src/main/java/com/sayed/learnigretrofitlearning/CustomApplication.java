package com.sayed.learnigretrofitlearning;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class CustomApplication extends Application {
    private static Application instance ;
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        if(instance == null) {
            instance = this ;
        }
    }

    public static Application getInstance () {
        return instance ;
    }

}
