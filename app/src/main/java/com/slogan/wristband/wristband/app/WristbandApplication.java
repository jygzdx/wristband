package com.slogan.wristband.wristband.app;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by free_boy on 2018/10/23.
 */

public class WristbandApplication extends Application {
    private static WristbandApplication instance;
    public static WristbandApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
