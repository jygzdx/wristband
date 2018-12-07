package com.slogan.wristband.wristband.utils;

import com.slogan.wristband.wristband.api.model.base.BaseResp;

public class ResponseUtils {
    public static final int SUCCESS_CODE = 200;
    public static boolean isSuccess(BaseResp resp){
        if(resp == null){
            ToastUtils.showToast("网络连接异常");
            return false;
        }else {
            if(resp.getCode() == SUCCESS_CODE){
                return true;
            }
            ToastUtils.showToast(resp.getMsg());
            return false;
        }
    }
}
