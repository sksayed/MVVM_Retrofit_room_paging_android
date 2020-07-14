package com.sayed.learnigretrofitlearning;

import android.app.Application;

public class CustomApplication extends Application {
    private static Application instance ;
    @Override
    public void onCreate() {
        super.onCreate();
        if(instance == null) {
            instance = this ;
        }
    }

    public static Application getInstance () {
        return instance ;
    }

}
