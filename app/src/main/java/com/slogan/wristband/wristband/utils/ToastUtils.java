package com.slogan.wristband.wristband.utils;

import android.widget.Toast;

import com.slogan.wristband.wristband.app.WristbandApplication;
import com.veclink.hw.bleservice.MsgService;

/**
 * Created by free_boy on 2018/11/2.
 */

public class ToastUtils {
    public static void showToast(String msg){
        Toast.makeText(WristbandApplication.getInstance(),
                msg,Toast.LENGTH_SHORT);
    }
}
