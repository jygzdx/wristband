package com.slogan.wristband.wristband.app;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.veclink.sdk.VeclinkSDK;

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
        VeclinkSDK veclinkSDK = VeclinkSDK.getInstance();
        veclinkSDK.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

}
