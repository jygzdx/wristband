package com.slogan.wristband.wristband.requestengine.entity;

import android.util.Log;

import com.google.gson.Gson;
import com.slogan.wristband.wristband.requestengine.factory.ErrInfo;
import com.slogan.wristband.wristband.requestengine.factory.HttpConnect;
import com.slogan.wristband.wristband.requestengine.factory.HttpMsg;
import com.slogan.wristband.wristband.utils.LogDataUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AccountRequest extends BaseRequest {
    private Gson gson;

    public AccountRequest(HttpMsg httpMsg, String url, int type) {
        this.type = type;
        this.httpMsg = httpMsg;
        this.url = url;
        httpConnect = new HttpConnect(this.url, AccountRequest.this);

        this.gson = new Gson();
    }


    @Override
    public void handleError(String err) {
        super.handleError(err);
        httpMsg.handleErrorInfo(err, err, 0, type, 0, 0);
    }

    @Override
    public void decodeResult(String result) {
        LogDataUtils.logData("返回数据", type + (result == null ? "" : result));
        try {
            JSONObject ob = new JSONObject(result);
            boolean state = ob.getBoolean("state");
            if (state) {
                switch (type) {


                }
            } else {

            }
        } catch (Exception e) {
            httpMsg.handleErrorInfo(ErrInfo.PARSE_ERROR, ErrInfo.PARSE_ERROR, 0, type, 0, 0);
        }

    }

}
