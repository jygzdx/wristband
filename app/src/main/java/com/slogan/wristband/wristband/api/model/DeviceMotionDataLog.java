package com.slogan.wristband.wristband.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备运动数据记录
 */
public class DeviceMotionDataLog implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     * 序号
     */
    private Long id;

    /**
     * 设备码
     */
    private String deviceCode;

    /**
     * 绑定用户编号
     */
    private Long bindUserId;

    /**
     * 公里
     */
    private String kilometre;

    /**
     * 大卡
     */
    private String kcal;

    /**
     * 步数
     */
    private String stepNum;

    /**
     * 小时
     */
    private String hour;

    /**
     * 久坐
     */
    private String sit;

    /**
     * 活动
     */
    private String activity;

    /**
     * 数据产生时间
     */
    private Date dataTime;

    public void setId( Long id ) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDeviceCode( String deviceCode ) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setBindUserId( Long bindUserId ) {
        this.bindUserId = bindUserId;
    }

    public Long getBindUserId() {
        return bindUserId;
    }

    public void setKilometre( String kilometre ) {
        this.kilometre = kilometre;
    }

    public String getKilometre() {
        return kilometre;
    }

    public void setKcal( String kcal ) {
        this.kcal = kcal;
    }

    public String getKcal() {
        return kcal;
    }

    public void setStepNum( String stepNum ) {
        this.stepNum = stepNum;
    }

    public String getStepNum() {
        return stepNum;
    }

    public void setHour( String hour ) {
        this.hour = hour;
    }

    public String getHour() {
        return hour;
    }

    public void setSit( String sit ) {
        this.sit = sit;
    }

    public String getSit() {
        return sit;
    }

    public void setActivity( String activity ) {
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
    }

    public void setDataTime( Date dataTime ) {
        this.dataTime = dataTime;
    }

    public Date getDataTime() {
        return dataTime;
    }
}

