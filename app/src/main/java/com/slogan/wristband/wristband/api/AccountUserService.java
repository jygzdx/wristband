package com.slogan.wristband.wristband.api;

import com.slogan.wristband.wristband.api.model.FrontUserInfo;
import com.slogan.wristband.wristband.api.model.base.BaseResp;
import com.slogan.wristband.wristband.api.model.base.ModelResp;
import com.slogan.wristband.wristband.api.model.base.TokenResp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 登录注册服务接口
 */
public interface AccountUserService {

    /**
     * 登录获取token
     *
     * @param username 账号，一般是手机号码
     * @param password base64编码后的密码
     * @return
     */
    @FormUrlEncoded
    @POST("/api/front/auth/token")
    Call<TokenResp> login(@Field("username") String username, @Field("password") String password);

    /**
     * 获取用户信息
     *
     * @param access_token
     * @return
     */
    @GET("/api/front/auth/user")
    Call<ModelResp<FrontUserInfo>> getUserInfo(@Header("access-token") String access_token);

    /**
     * 刷新token信息
     *
     * @param access_token
     * @return
     */
    @GET("/api/front/auth/refresh")
    Call<TokenResp> refreshToken(@Header("access-token") String access_token);

    /**
     * token注销
     *
     * @param access_token
     * @return
     */
    @GET("/api/front/auth/invalid")
    Call<BaseResp> invalid(@Header("access-token") String access_token);

    /**
     * 验证token是否过期
     *
     * @param access_token
     * @return
     */
    @GET("/api/front/auth/validateToken")
    Call<BaseResp> validateToken(@Header("access-token") String access_token);


    /**
     * 检查手机号码
     *
     * @param mobile
     * @return
     */
    @POST("/api/front/register/checkMobile")
    Call<BaseResp> checkMobile(@Query("mobile") String mobile);

    /**
     * 发送验证码
     *
     * @param mobile
     * @return
     */
    @POST("/api/front/register/sendSms")
    Call<BaseResp> sendMsg(@Query("mobile") String mobile);

    /**
     * 注册
     *
     * @param username 手机号码
     * @param password base64编码后的密码
     * @param validCode 验证码
     * @return
     */
    @POST("/api/front/register/register")
    Call<BaseResp> register(@Query("username") String username, @Query("password") String password, @Query("validCode") String validCode);

    /**
     * 重置密码
     *
     * @param mobile 手机号码
     * @param newPwd base64编码后的密码
     * @param validCode 验证码
     * @return
     */
    @POST("/api/front/register/resetPassword")
    Call<BaseResp> resetPassword(@Query("username") String mobile, @Query("newPwd") String newPwd, @Query("validCode") String validCode);

}
