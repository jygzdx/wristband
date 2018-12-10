package com.slogan.wristband.wristband.utils;

import com.slogan.wristband.wristband.api.model.base.BaseResp;
import com.slogan.wristband.wristband.app.WristbandApplication;

public class ResponseUtils {
    public static final int SUCCESS_CODE = 200;
    public static boolean isSuccess(BaseResp resp){
        if(resp == null){
            WristbandApplication.getInstance().showToast("网络连接异常");
            return false;
        }else {
            if(resp.getCode() == SUCCESS_CODE){
                return true;
            }
            WristbandApplication.getInstance().showToast(resp.getMsg());
            return false;
        }
    }
}
