package com.slogan.wristband.wristband.utils;

import android.content.Context;
import android.widget.Toast;

import com.slogan.wristband.wristband.app.WristbandApplication;

/**
 * Created by free_boy on 2018/11/2.
 */

public class ToastUtils {
    public static void showToast(String msg){
        Toast.makeText(WristbandApplication.getInstance(),
                msg,Toast.LENGTH_SHORT);
    }
    public static void showToast(Context context,String msg){
        Toast.makeText(context,
                msg,Toast.LENGTH_SHORT);
    }
}
