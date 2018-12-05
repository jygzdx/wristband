package com.slogan.wristband.wristband.api.model;

import java.io.Serializable;
import java.util.Date;

public class DeviceWarningLog implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    /**
     * 序号
     */
    private Long id;

    /**
     * 设备编码
     */
    private String driverCode;

    /**
     * 绑定用户编号
     */
    private Long bindUserId;

    /**
     * 预警类型(1心率2血糖)
     */
    private Integer warningType;

    /**
     * 预警内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    /**
     * 跟进人Id
     */
    private Long followAccountId;

    /**
     * 跟进时间
     */
    private Date followTime;

    /**
     * 跟进处理情况
     */
    private String followRemark;

    public void setId( Long id ) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setDriverCode( String driverCode ) {
        this.driverCode = driverCode;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public void setBindUserId( Long bindUserId ) {
        this.bindUserId = bindUserId;
    }

    public Long getBindUserId() {
        return bindUserId;
    }

    public void setWarningType( Integer warningType ) {
        this.warningType = warningType;
    }

    public Integer getWarningType() {
        return warningType;
    }

    public void setContent( String content ) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setRemark( String remark ) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setFollowAccountId( Long followAccountId ) {
        this.followAccountId = followAccountId;
    }

    public Long getFollowAccountId() {
        return followAccountId;
    }

    public void setFollowTime( Date followTime ) {
        this.followTime = followTime;
    }

    public Date getFollowTime() {
        return followTime;
    }

    public void setFollowRemark( String followRemark ) {
        this.followRemark = followRemark;
    }

    public String getFollowRemark() {
        return followRemark;
    }
}
