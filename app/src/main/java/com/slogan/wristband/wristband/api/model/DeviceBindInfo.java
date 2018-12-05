package com.slogan.wristband.wristband.api.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户绑定设备信息
 */
public class DeviceBindInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     * 序号
     */
    private Integer id;

    /**
     * 绑定用户编号
     */
    private Long bindUserId;

    /**
     * 设备码
     */
    private String deviceCode;

    /**
     * 设备类型
     */
    private Integer deviceType;

    /**
     * 设备型号
     */
    private String deviceVersion;

    /**
     * 设备状态
     */
    private Integer deviceState;

    /**
     * 绑定时间
     */
    private Date bindTime;

    /**
     * 解绑时间
     */
    private Date unbindTime;

    /**
     * 最新数据上传时间
     */
    private Date lastDataTime;

    /**
     * 设置睡眠时间(小时)
     */
    private Integer settingSleep;

    /**
     * 设置运动步数
     */
    private Integer settingStep;

    public void setId( Integer id ) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setBindUserId( Long bindUserId ) {
        this.bindUserId = bindUserId;
    }

    public Long getBindUserId() {
        return bindUserId;
    }

    public void setDeviceCode( String deviceCode ) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceType( Integer deviceType ) {
        this.deviceType = deviceType;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceVersion( String deviceVersion ) {
        this.deviceVersion = deviceVersion;
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceState( Integer deviceState ) {
        this.deviceState = deviceState;
    }

    public Integer getDeviceState() {
        return deviceState;
    }

    public void setBindTime( Date bindTime ) {
        this.bindTime = bindTime;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setUnbindTime( Date unbindTime ) {
        this.unbindTime = unbindTime;
    }

    public Date getUnbindTime() {
        return unbindTime;
    }

    public void setLastDataTime( Date lastDataTime ) {
        this.lastDataTime = lastDataTime;
    }

    public Date getLastDataTime() {
        return lastDataTime;
    }

    public void setSettingSleep( Integer settingSleep ) {
        this.settingSleep = settingSleep;
    }

    public Integer getSettingSleep() {
        return settingSleep;
    }

    public void setSettingStep( Integer settingStep ) {
        this.settingStep = settingStep;
    }

    public Integer getSettingStep() {
        return settingStep;
    }
}
