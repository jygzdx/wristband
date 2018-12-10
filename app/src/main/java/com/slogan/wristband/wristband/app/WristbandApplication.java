package com.slogan.wristband.wristband.app;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.slogan.wristband.wristband.utils.StringUtils;
import com.slogan.wristband.wristband.widght.CommToast;
import com.veclink.sdk.VeclinkSDK;

/**
 * Created by free_boy on 2018/10/23.
 */

public class WristbandApplication extends Application {
    private static WristbandApplication instance;
    private CommToast commToast;

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
    public void showToast(String toast) {
        if (!StringUtils.isBlank(toast)) {
            if (commToast == null) {
                commToast = new CommToast();
            }
            commToast.showToast(WristbandApplication.this, toast);
        }
    }
}
