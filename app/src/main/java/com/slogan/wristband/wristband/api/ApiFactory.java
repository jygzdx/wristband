package com.slogan.wristband.wristband.api;

import com.slogan.wristband.wristband.requestengine.factory.OkHttpUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 接口服务调用工厂类
 */
public final class ApiFactory {

    private static final String BASE_URL = "https://www.bestgo.tech/shop-service-api/";

    private ApiFactory() {
    }

    public static Retrofit provideRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpUtils.getInstance().getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 用户相关接口服务
     *
     * @return
     */
    public static AccountUserService provideAccountUserService() {
        return provideRetrofit(BASE_URL).create(AccountUserService.class);
    }

    /**
     * 设备相关服务
     *
     * @return
     */
    public static DeviceDataService provideDeviceDataService() {
        return provideRetrofit(BASE_URL).create(DeviceDataService.class);
    }

}
