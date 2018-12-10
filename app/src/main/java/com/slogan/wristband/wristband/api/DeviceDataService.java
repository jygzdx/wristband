package com.slogan.wristband.wristband.api;


import com.slogan.wristband.wristband.api.model.DeviceBindInfo;
import com.slogan.wristband.wristband.api.model.DeviceBindInfoListVO;
import com.slogan.wristband.wristband.api.model.DeviceHealthDataLog;
import com.slogan.wristband.wristband.api.model.DeviceMotionDataLog;
import com.slogan.wristband.wristband.api.model.DeviceWarningLog;
import com.slogan.wristband.wristband.api.model.base.BaseResp;
import com.slogan.wristband.wristband.api.model.base.PageResp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 设备相关服务接口
 */
public interface DeviceDataService {

    /**
     * 获取绑定设备信息表列表
     *
     * @param access_token 令牌
     * @param pageNo 当前页号
     * @param pageSize 每页大小
     * @return
     */
    @GET("api/front/deviceBind/list")
    Call<PageResp<DeviceBindInfoListVO>> deviceBindList(@Header("access-token") String access_token, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);


    /**
     * 设备绑定用户
     * @param access_token 令牌
     * @param deviceBindInfo 设备信息
     * @return
     */
    @POST("api/front/deviceBind/create")
    Call<BaseResp> deviceBindCreate(@Header("access-token") String access_token, @Body DeviceBindInfo deviceBindInfo);

    /**
     * 用户解绑设备
     * @param access_token
     * @param deviceCodeNo 设备编码
     * @return
     */
    @POST("api/front/deviceBind/unbind/{deviceCodeNo}")
    Call<BaseResp> deviceUnbindCreate(@Header("access-token") String access_token, @Path("deviceCodeNo") String deviceCodeNo);

    /**
     * 设备健康数据记录表列表
     * @param access_token
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("api/front/deviceHealth/list")
    Call<PageResp<DeviceHealthDataLog>> deviceHealthList(@Header("access-token") String access_token, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    /**
     * 新增设备健康数据记录
     * @param access_token
     * @param deviceHealthDataLog
     * @return
     */
    @POST("api/front/deviceHealth/create")
    Call<BaseResp> deviceHealthCreate(@Header("access-token") String access_token, @Body DeviceHealthDataLog deviceHealthDataLog);

    /**
     * 设备运动数据记录表列表
     * @param access_token
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("api/front/deviceMotion/list")
    Call<PageResp<DeviceMotionDataLog>> deviceMotionList(@Header("access-token") String access_token, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    /**
     * 新增设备运动数据记录
     * @param access_token
     * @param deviceMotionDataLog
     * @return
     */
    @POST("api/front/deviceMotion/create")
    Call<BaseResp> deviceMotionCreate(@Header("access-token") String access_token, @Body DeviceMotionDataLog deviceMotionDataLog);

    /**
     * 设备预警记录表列表
     * @param access_token
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GET("api/front/deviceWarningLog/list")
    Call<PageResp<DeviceWarningLog>> deviceWarningLogList(@Header("access-token") String access_token, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    /**
     * 设备预警记录表新增
     * @param access_token
     * @param deviceWarningLog
     * @return
     */
    @POST("api/front/deviceWarningLog/create")
    Call<BaseResp> deviceWarningLogCreate(@Header("access-token") String access_token, @Body DeviceWarningLog deviceWarningLog);
}
