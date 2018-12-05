package com.slogan.wristband.wristband.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备健康数据记录
 */
public class DeviceHealthDataLog implements Serializable {

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
     * 心率
     */
    private String heartRate;

    /**
     * 血压
     */
    private String bloodPressure;

    /**
     * 血氧
     */
    private String bloodOxygen;

    /**
     * 深度睡眠
     */
    private String sleepOne;

    /**
     * 浅度睡眠
     */
    private String sleepTwo;

    /**
     * 睡眠总时
     */
    private String sleepTotal;

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

    public void setHeartRate( String heartRate ) {
        this.heartRate = heartRate;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setBloodPressure( String bloodPressure ) {
        this.bloodPressure = bloodPressure;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodOxygen( String bloodOxygen ) {
        this.bloodOxygen = bloodOxygen;
    }

    public String getBloodOxygen() {
        return bloodOxygen;
    }

    public void setSleepOne( String sleepOne ) {
        this.sleepOne = sleepOne;
    }

    public String getSleepOne() {
        return sleepOne;
    }

    public void setSleepTwo( String sleepTwo ) {
        this.sleepTwo = sleepTwo;
    }

    public String getSleepTwo() {
        return sleepTwo;
    }

    public void setSleepTotal( String sleepTotal ) {
        this.sleepTotal = sleepTotal;
    }

    public String getSleepTotal() {
        return sleepTotal;
    }

    public void setDataTime( Date dataTime ) {
        this.dataTime = dataTime;
    }

    public Date getDataTime() {
        return dataTime;
    }
}
