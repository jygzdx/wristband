package com.slogan.wristband.wristband.requestengine.factory;


import com.slogan.wristband.wristband.app.WristbandApplication;
import com.slogan.wristband.wristband.db.AppConfigSharedPreferences;
import com.slogan.wristband.wristband.db.UserAppConfig;
import com.slogan.wristband.wristband.db.UserInfoConfig;
import com.slogan.wristband.wristband.db.UserInfoSharedPreference;
import com.slogan.wristband.wristband.utils.CommTool;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author bin
 */
public class HttpConnect {

    private String url;

    private IHttpListener processor;

    public final String REQUEST_TIME_OUT = "请求失败，请稍后再试...";

    public final String REQUEST_NET_ERROR = "当前网络不可用，请检查后再试";

    public HttpConnect(String url, IHttpListener processor) {
        this.url = url;
        this.processor = processor;
    }

    public void postData(JSONObject jsonObject){
        if (!CommTool.isNetworkConnected(WristbandApplication.getInstance())) {
            processor.handleError(REQUEST_NET_ERROR);
            return;
        }
      //  System.out.println("请求地址----"+url+"\n请求数据---"+jsonObject.toString());
        OkHttpClient okHttpClient=  OkHttpUtils.getInstance().getOkHttpClient();
        try{
            RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonObject.toString());
            Request.Builder builder=new Request.Builder().post(requestBody).url(url).addHeader("headKey",getHeadInfo());
            Request request=builder.build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(CommTool.getErrorStack(e,-1));
                    processor.handleError(REQUEST_TIME_OUT);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    processor.decodeResult(response.body().string());
                }
            });
        }catch (Exception e){
            processor.handleError(REQUEST_TIME_OUT);
        }


    }
    public String getHeadInfo() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("osType", 0);
            jsonObject.put("version", CommTool.getVersionCode(WristbandApplication.getInstance()));
            jsonObject.put("channel", CommTool.getChannel(WristbandApplication.getInstance()));
            jsonObject.put("imei", CommTool.getDeviceId(WristbandApplication.getInstance()));
            jsonObject.put("imei", CommTool.getDeviceId(WristbandApplication.getInstance()));
            jsonObject.put("uid", UserInfoSharedPreference.getUserInfoLong(WristbandApplication.getInstance(), UserInfoConfig.USER_ID, 0));
            jsonObject.put("loginKey", UserInfoSharedPreference.getUserInfoString(WristbandApplication.getInstance(), UserInfoConfig.LOGIN_KEY, ""));
            jsonObject.put("mobileVersion",
                    AppConfigSharedPreferences.getAppInfoString(WristbandApplication.getInstance(), UserAppConfig.MOBILEVERSION, ""));
            jsonObject.put("deviceModel", AppConfigSharedPreferences.getAppInfoString(WristbandApplication.getInstance(), UserAppConfig.DEVICE_MODEL, ""));
        } catch (Exception e) {
        }
        return jsonObject.toString();
    }



}
